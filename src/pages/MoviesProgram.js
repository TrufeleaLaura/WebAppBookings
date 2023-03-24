import { useState, useEffect, createContext } from "react";
import React from "react";
import axios from "axios";
import dayjs from "dayjs";
import { Link, useNavigate } from "react-router-dom";
import {
  Table,
  Card,
  Button,
  Modal,
  Form,
  Input,
  DatePicker,
  Select,
  Option,
  TimePicker,
} from "antd";

export default function MoviesProgram() {
  const [movies, setMovies] = useState([]);
  const [deleteModalVisible, setDeleteModalVisible] = useState(false);
  const [updateModalVisible, setUpdateModalVisible] = useState(false);
  const [selectedMovie, setSelectedMovie] = useState(null);
  const [moviesNames, setMoviesNames] = useState([]);
  const navigate = useNavigate();
  const format = "HH:mm:ss";
  const dateFormat = "YYYY-MM-DD";

  useEffect(() => {
    axios
      .get("http://localhost:8080/movies/all", {
        headers: {
          "Access-Control-Allow-Origin": "*",
        },
      })
      .then((response) => {
        setMoviesNames(response.data);
      })
      .catch((error) => {
        console.log(error);
      });
  }, []);

  useEffect(() => {
    axios
      .get("http://localhost:8080/moviesProgram/all", {
        headers: {
          "Access-Control-Allow-Origin": "*",
        },
      })
      .then((res) => {
        setMovies(res.data);
      })
      .catch((err) => console.log(err));
  }, [movies]);

  const columns = [
    {
      title: "Name",
      dataIndex: "name",
      key: "name",
    },
    {
      title: "Start Time",
      dataIndex: "startTime",
      key: "startTime",
    },
    {
      title: "End Time",
      dataIndex: "endTime",
      key: "endTime",
    },
    {
      title: "Date",
      dataIndex: "date",
      key: "date",
    },
    {
      title: "Room",
      dataIndex: "room",
      key: "room",
    },
    {
      title: "Actions",
      key: "actions",
      render: (text, record) => (
        <div>
          
          <Button
            type="link"
            style={{ color: "orange" }}
            onClick={() => handleDelete(record)}
          >
            Delete
          </Button>
        </div>
      ),
    },
  ];
  const handleDelete = (record) => {
    setSelectedMovie(record);
    setDeleteModalVisible(true);
  };

  const handleDeleteConfirm = () => {
    console.log(selectedMovie.key);
    axios
      .delete(`http://localhost:8080/moviesProgram/delete/${selectedMovie.key}`)
      .then(() => {
        setDeleteModalVisible(false);
        setMovies(movies.filter((movie) => movie.key !== selectedMovie.key));
      })
      .catch((err) => console.log(err));
  };

  

  const redirect = () => {
    navigate("/addMovieProgram");
  };
  return (
    <div>
      <div
        style={{
          display: "flex",
          justifyContent: "space-between",
          alignItems: "center",
          marginBottom: "16px",
        }}
      >
        <Button
          type="primary"
          onClick={redirect}
          style={{
            color: "white",
            backgroundColor: "orange",
            padding: "5px",
            margin: "20px",
          }}
        >
          +Add Movie Program
        </Button>
      </div>
      <Table
        dataSource={movies.map((movie) => ({
          key: movie.programId,
          name: movie.movieId.name,
          startTime: movie.startTime,
          endTime: movie.endTime,
          date: movie.date,
          room: movie.room.name,
        }))}
        columns={columns}
      />
      <Modal
        title="Delete Movie"
        open={deleteModalVisible}
        onCancel={() => setDeleteModalVisible(false)}
        onOk={handleDeleteConfirm}
        okButtonProps={{ style: { backgroundColor: "orange" } }}
      >
        <p>Are you sure you want to delete {selectedMovie?.name}?</p>
      </Modal>
     
    </div>
  );
}
