import { useState, useEffect, createContext } from "react";
import React from "react";
import axios from "axios";
import dayjs from "dayjs";
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

export default function RegistredUsers() {
  const [usersData, setUsersData] = useState([]);

  const columns = [
    {
      title: "Name",
      dataIndex: "name",
      key: "name",
    },
    {
      title: "Surname",
      dataIndex: "surname",
      key: "surname",
    },
    {
      title: "Email",
      dataIndex: "email",
      key: "email",
    },
    {
      title: "Number of Tickets Bought",
      dataIndex: "numberOfTickets",
      key: "numberOfTickets",
    },
  ];

  useEffect(() => {
    axios
      .get("http://localhost:8080/users/all", {
        headers: {
          "Access-Control-Allow-Origin": "*",
        },
      })
      .then(async (response) => {
        const usersWithNumberOfTickets = await Promise.all(
          response.data.map(async (user) => {
            const usersResponse = await axios.get(
              `http://localhost:8080/tickets/purchasedTicketsPerUser/${user.userId}`
            );
            const numberOfTickets = usersResponse.data;
            return {
              ...user,
              key: user.userId,
              name: user.name,
              surname: user.surname,
              email: user.email,
              numberOfTickets: numberOfTickets,
            };
          })
        );
        setUsersData(usersWithNumberOfTickets);
      })
      .catch((error) => console.error(error));
  }, []);

  return <Table columns={columns} dataSource={usersData} />;
}
