import React, { useState, useEffect } from "react";

import { Form, Input, Select, Button, DatePicker, TimePicker } from "antd";
import axios from "axios";
import { Link } from "react-router-dom";
import moment from "moment";

const { Option } = Select;

export default function AddMovieProgram() {
  const [form] = Form.useForm();
  const [movie, setSelectedMovie] = useState([]);
  const [date, setDate] = useState(null);
  const [room, setSelectedRoom] = useState(null);
  const [startTime, setStartTime] = useState(null);
  const [endTime, setEndTime] = useState(null);
  const [movies, setMovies] = useState([]);
  const [rooms, setRooms] = useState([]);

  useEffect(() => {
    axios
      .get("http://localhost:8080/movies/all", {
        headers: {
          "Access-Control-Allow-Origin": "*",
        },
      })
      .then((res) => {
        setMovies(res.data);
      })
      .catch((err) => console.log(err));

    axios
      .get("http://localhost:8080/rooms/allRooms", {
        headers: {
          "Access-Control-Allow-Origin": "*",
        },
      })
      .then((res) => {
        setRooms(res.data);
      })
      .catch((err) => console.log(err));
  }, []);

  const handleMovieSelect = (value) => {
    setSelectedMovie(movies.find((movie) => movie.movieId === value));
  };

  const handleRoomSelect = (value) => {
    setSelectedRoom(rooms.find((room) => room.roomId === value));
  };

  const handleDateChange = (date, dateString) => {
    setDate(dateString);
  };

  const handleStartTimeChange = (time, timeString) => {
    setStartTime(timeString);
  };

  const handleEndTimeChange = (time, timeString) => {
    setEndTime(timeString);
  };

  const validateTime = (_, value) => {
    if (!value) {
      return Promise.reject(new Error("Please pick time! "));
    }

    return Promise.resolve();
  };

  const onFinish = async (values) => {
    const { name, room, date, startTime, endTime } = values;
    const program = {
      movieId: name,
      roomId: room,
      date: date.format("YYYY-MM-DD"),
      startTime: startTime.format("HH:mm:ss"),
      endTime: endTime.format("HH:mm:ss"),
    };
    try {
      await axios.post(
        `http://localhost:8080/moviesProgram/addMovieProgram`,
        program
      );
      form.resetFields();
      setDate(null);
      alert("Program added successfully!");
    } catch (error) {
      console.error(error);
      if (error.response && error.response.data) {
        alert(error.response.data.message);
      } else {
        alert("Failed to add program!");
      }
    }
  };

  useEffect(() => {
    axios
      .get("http://localhost:8080/movies/all", {
        headers: {
          "Access-Control-Allow-Origin": "*",
        },
      })
      .then((res) => {
        setMovies(res.data);
      })
      .catch((err) => console.log(err));
  }, []);

  return (
    <div
      style={{
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
        minHeight: "100vh",
        backgroundImage:
          "url('https://thumbs.dreamstime.com/b/clapperboard-pop-corn-orange-color-background-cinema-film-concept-fresh-salty-movie-clapper-board-banner-145416811.jpg')",
        backgroundSize: "cover",
        backgroundPosition: "center",
      }}
    >
      <Form
        form={form}
        onFinish={onFinish}
        layout="vertical"
        style={{
          width: "50%",
          padding: "90px",
          backgroundColor: "#fff",
          borderRadius: "20px",
        }}
      >
        <Form.Item
          name="name"
          label="Movie Name"
          rules={[{ required: true, message: "Please select the movie" }]}
        >
          <Select
            placeholder="Select a movie"
            onChange={handleMovieSelect}
            allowClear
          >
            {movies.map((m) => (
              <Option key={m.movieId} value={m.movieId}>
                {m.name}
              </Option>
            ))}
          </Select>
        </Form.Item>
        <Form.Item
          name="date"
          label="Date"
          rules={[{ required: true, message: "Please select the date" }]}
        >
          <DatePicker onChange={handleDateChange} />
        </Form.Item>
        <Form.Item
          name="startTime"
          label="Start Time"
          rules={[{ required: true, validator: validateTime }]}
        >
          <TimePicker format="HH:mm:ss" onChange={handleStartTimeChange} />
        </Form.Item>
        <Form.Item
          name="endTime"
          label="End Time"
          rules={[{ required: true, validator: validateTime }]}
        >
          <TimePicker format="HH:mm:ss" onChange={handleEndTimeChange} />
        </Form.Item>
        <Form.Item
          name="room"
          label="Room"
          rules={[{ required: true, message: "Please select the room" }]}
        >
          <Select
            placeholder="Select a room"
            onChange={handleRoomSelect}
            allowClear
          >
            {rooms.map((room) => (
              <Option key={room.roomId} value={room.roomId}>
                {room.name}
              </Option>
            ))}
          </Select>
        </Form.Item>
        <Form.Item>
          <Button type="primary" htmlType="submit">
            Add Program
          </Button>
        </Form.Item>
      </Form>
    </div>
  );
}
