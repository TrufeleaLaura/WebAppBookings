import React, { useState, useEffect } from "react";
import { Table } from "antd";
import axios from "axios";

export default function Reservations() {
  const [data, setData] = useState([]);
  const [searchTerm, setSearchTerm] = useState("");
  const filteredData = data.filter((reservation) =>
    reservation.movieName.toLowerCase().includes(searchTerm.toLowerCase())
  );

  useEffect(() => {
    const fetchData = async () => {
      const response = await axios.get(
        `http://localhost:8080/tickets/allReservations`
      );
      const reservations = response.data;
      const formattedData = reservations.map((reservation) => {
        return {
          email: reservation.email,
          seats: reservation.seats,
          movieName: reservation.movieName,
          movieDate: reservation.movieDate,
          movieStartTime: reservation.movieStartTime,
        };
      });
      setData(formattedData);
    };

    fetchData();
  });

  const columns = [
    {
      title: "Email User",
      dataIndex: "email",
      key: "email",
    },
    {
      title: "Seats",
      dataIndex: "seats",
      key: "seats",
      render: (seats) => (
        <ul>
          {seats.map((seat) => (
            <li key={seat}>{seat}</li>
          ))}
        </ul>
      ),
    },
    {
      title: "Program Name",
      dataIndex: "movieName",
      key: "movieName",
    },
    {
      title: "Program Date",
      dataIndex: "movieDate",
      key: "movieDate",
    },
    {
      title: "Program Time",
      dataIndex: "movieStartTime",
      key: "movieStartTime",
    },
  ];

  return (
    <div style={{ position: "relative" }}>
      <Table columns={columns} dataSource={filteredData} />
      <div
        style={{
          position: "absolute",
          bottom: 0,
          left: "50%",
          transform: "translate(-50%)",
        }}
      >
        <input
          type="text"
          placeholder="Search by movie name"
          value={searchTerm}
          style={{
            width: "200px",
            height: "30px",
            borderRadius: "5px",
            border: "1px solid #ccc",
            padding: "0 10px",
          }}
          onChange={(e) => setSearchTerm(e.target.value)}
        />
      </div>
    </div>
  );
}
