import { useState, useEffect } from "react";
import { Table } from "antd";
import axios from "axios";
import { useAuth } from "../auth/AuthContext";

export default function UserTickets() {
  const [ticketsData, setTicketsData] = useState([]);
  const { getUser } = useAuth();
  const user = getUser();

  const columns = [
    {
      title: "Seat Number",
      dataIndex: "seat",
      key: "seat",
    },
    {
      title: "Date Of Reservation",
      dataIndex: "buyDate",
      key: "buyDate",
    },
    {
      title: "Movie Name",
      dataIndex: "movieName",
      key: "movieName",
    },
    {
      title: "Movie Date",
      dataIndex: "date",
      key: "date",
    },
    {
      title: "StartTime",
      dataIndex: "startTime",
      key: "startTime",
    },
    {
      title: "Room Number",
      dataIndex: "roomName",
      key: "roomName",
    },
  ];

  useEffect(() => {
    const fetchData = async () => {
      const response = await axios.get(
        `http://localhost:8080/tickets/allTicketsForUser/${user.id}`,
        {
          headers: {
            "Access-Control-Allow-Origin": "*",
          },
        }
      );
      const tickets = response.data;
      const formattedData = tickets.map((ticket) => {
        return {
          seat: ticket.seat,
          movieName: ticket.movieName,
          buyDate: ticket.buyDate,
          date: ticket.date,
          startTime: ticket.startTime,
          roomName: ticket.roomName,
        };
      });
      setTicketsData(formattedData);
    };

    fetchData();
  });

  return <Table columns={columns} dataSource={ticketsData} />;
}
