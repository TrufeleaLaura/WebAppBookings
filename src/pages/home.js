import React, { useState, useEffect } from "react";
import axios from "axios";
import {
  Grid,
  Box,
  Card,
  CardActions,
  CardContent,
  CardMedia,
  Button,
  Typography,
} from "@mui/material";
import { Modal } from "antd";
import { Link, useNavigate } from "react-router-dom";
import { useAuth } from "../auth/AuthContext";

function Home() {
  const [movies, setMovies] = useState([]);
  const [deleteModalVisible, setDeleteModalVisible] = useState(false);
  const [selectedMovie, setSelectedMovie] = useState(null);
  const { userIsAuthenticated, getUser } = useAuth();
  const auU = userIsAuthenticated();
  const user = getUser();
  const navigate = useNavigate();

  useEffect(() => {
    axios
      .get("http://localhost:8080/movies/all", {
        headers: {
          "Access-Control-Allow-Origin": "*",
        },
      })
      .then((response) => {
        setMovies(response.data);
      })
      .catch((error) => {
        console.log(error);
      });
  }, [movies]);

  const handleDelete = (record) => {
    setSelectedMovie(record);
    setDeleteModalVisible(true);
  };
  const handleDeleteConfirm = () => {
    console.log(selectedMovie.movieId);
    axios
      .delete(`http://localhost:8080/movies/delete/${selectedMovie.movieId}`)
      .then(() => {
        setDeleteModalVisible(false);
        setMovies(movies.filter((movie) => movie.key !== selectedMovie.key));
      })
      .catch((err) => console.log(err));
  };
  const handleBookNowClick = (record) => {
    console.log(record);
    console.log("Book Now Clicked");
    navigate(`/booking/${record.movieId}`);
  };

  return (
    <Grid container spacing={2} justifyContent="center">
      {movies.map((movie) => (
        <Grid item xs={12} sm={6} md={4} key={movie.movieId}>
          <Card sx={{ margin: "1rem" }}>
            <CardMedia
              component="img"
              height="400"
              image={movie.imageUrl}
              alt={movie.name}
            />
            <CardContent>
              <Typography gutterBottom variant="h5" component="div">
                {movie.name}
              </Typography>
              <Typography variant="body2" color="text.secondary">
                {movie.category.toUpperCase()} - {movie.type}
              </Typography>
            </CardContent>
            {/* <CardActions> */}
            {auU && user.role != "Manager" && (
              <CardActions>
                <Button
                  onClick={() => handleBookNowClick(movie)}
                  size="large"
                  sx={{ color: "orange" }}
                >
                  Book Now
                </Button>
              </CardActions>
            )}
            {auU && user.role == "Manager" && (
              <CardActions>
                <Button
                  component={Link}
                  onClick={() => handleDelete(movie)}
                  size="large"
                  sx={{ color: "orange" }}
                >
                  Delete Movie
                </Button>
              </CardActions>
            )}
            {!auU && (
              <CardActions>
                <Button
                  component={Link}
                  to={`/login`}
                  size="large"
                  sx={{ color: "orange" }}
                >
                  Book Now
                </Button>
              </CardActions>
            )}
            {/* </CardActions> */}
          </Card>
        </Grid>
      ))}
      <Modal
        title="Delete Movie"
        open={deleteModalVisible}
        onCancel={() => setDeleteModalVisible(false)}
        onOk={handleDeleteConfirm}
        okButtonProps={{ style: { backgroundColor: "orange" } }}
      >
        <p>Are you sure you want to delete {selectedMovie?.name}?</p>
      </Modal>
    </Grid>
  );
}

export default Home;
