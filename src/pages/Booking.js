import { useEffect, useState } from "react";
import { useParams,useNavigate } from "react-router-dom";
import { Radio, Button, Space, Checkbox, Row } from "antd";
import axios from "axios";
import { useAuth } from "../auth/AuthContext";

export default function Booking() {
  const { id } = useParams();
  const [movie, setMovie] = useState([]);
  const [availableShowtimes, setAvailableShowtimes] = useState([]);
  const [selectedShowTime, setSelectedShowTime] = useState("");
  const [errorMessage, setErrorMessage] = useState(null);
  const [selectedShowtime, setSelectedShowtime] = useState(null);
  const [selectedShowtimeSeats, setSelectedShowtimeSeats] = useState([]);
  const [selectedSeats, setSelectedSeats] = useState([]);
  const { getUser } = useAuth();
  const user = getUser();
  const navigate=useNavigate();

  const handleShowtimeChange = (e) => {
    const selectedShowtime = e.target.value;
    setSelectedShowtime(selectedShowtime);
    setSelectedShowtimeSeats(selectedShowtime.seats);
  };

  useEffect(() => {
    console.log(user)
    axios
      .get(`http://localhost:8080/movies/find/${id}`, {
        headers: {
          "Access-Control-Allow-Origin": "*",
        },
      })
      .then((response) => {
        setMovie(response.data);
      })
      .catch((error) => {
        console.log(error);
      });
  }, [id]);

  useEffect(() => {
    axios
      .get(`http://localhost:8080/moviesProgram/findTimes/${id}`, {
        headers: {
          "Access-Control-Allow-Origin": "*",
        },
      })
      .then((response) => {
        setAvailableShowtimes(response.data);
      })
      .catch((error) => {
        if (error.response.status === 404) {
          setErrorMessage("No showtimes available for this movie");
        }
      });
  }, [id]);

  function handleSeatChange(checkedValues) {
    setSelectedSeats(checkedValues);
  }

  const handleBookNowClick = async () => {
    try {
      for (let i = 0; i < selectedSeats.length; i++) {
        const seat = selectedSeats[i];
        const data = {
          seatNumber: seat,
          programId: selectedShowtime.programId,
          userId: user.id,
        };
        const response = await axios.post(
          `http://localhost:8080/tickets/addTicket`,
          data
        );
        console.log(response.data);
      }
        navigate("/");
    } catch (error) {
      console.error(error);
      alert("Error booking seats");
      setErrorMessage("Error booking seats");
    }
  };
  

  return (
    <div
      style={{
        display: "flex",
        flexDirection: "column",
        alignItems: "center",
        justifyContent: "center",
        minHeight: "100vh",
        backgroundImage:
          "url('https://thumbs.dreamstime.com/b/clapperboard-pop-corn-orange-color-background-cinema-film-concept-fresh-salty-movie-clapper-board-banner-145416811.jpg')",
        backgroundSize: "cover",
        backgroundPosition: "center",
      }}
    >
      <div
        style={{
          backgroundColor: "white",
          padding: "20px",
          borderRadius: "10px",
          width: "50%",
          display: "flex",
          flexDirection: "column",
          alignItems: "center",
        }}
      >
        <h1>{movie?.name}</h1>

        <p>Select a showtime:</p>
        <Radio.Group defaultValue={null} onChange={handleShowtimeChange}>
          <Space direction="vertical">
            {availableShowtimes.map((showtime, index) => (
              <Radio style={{ fontSize: "20px" }} key={index} value={showtime}>
                {showtime.date} {showtime.startTime}-{showtime.endTime}
              </Radio>
            ))}
          </Space>
        </Radio.Group>
        {selectedShowtime && (
          <div>
            <p>Seats available:</p>
            <div style={{ maxWidth: "70%", overflow: "auto", padding: "20px" }}>
              <Checkbox.Group
                style={{ display: "flex", justifyContent: "center" }}
                onChange={handleSeatChange}
              >
                <Row gutter={16}>
                  {selectedShowtime.seats.map((seat, index) => (
                    <Checkbox key={index} value={seat}>
                      {seat}
                    </Checkbox>
                  ))}
                </Row>
              </Checkbox.Group>
            </div>
          </div>
        )}
        <div style={{ marginTop: "20px" }}>
          <Button onClick={handleBookNowClick}>Book Now</Button>
        </div>
      </div>
    </div>
  );
}
