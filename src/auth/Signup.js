import React, { Component, useContext, useState, useEffect } from "react";
import { NavLink, Navigate, useNavigate } from "react-router-dom";
import { Button, Form, Grid, Segment, Message } from "semantic-ui-react";
import AuthContext from "./AuthContext";
import { MoviesApi } from "../components/MoviesApi";
import { parseJwt, handleLogError } from "./Helpers";

const Signup = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [name, setName] = useState("");
  const [surname, setSurname] = useState("");
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [isError, setIsError] = useState(false);
  const [errorMessage, setErrorMessage] = useState("");
  const Auth = useContext(AuthContext);
  const navigate = useNavigate();

  useEffect(() => {
    const isLoggedIn = Auth.userIsAuthenticated();
    setIsLoggedIn(isLoggedIn);
  }, [Auth]);

  const handleInputChange = (e, { name, value }) => {
    if (name === "email") setEmail(value);
    if (name === "name") setName(value);
    if (name === "surname") setSurname(value);
    if (name === "password") setPassword(value);
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    if (!(surname && password && name && email)) {
      setIsError(true);
      setErrorMessage("Please, inform all fields!");
      return;
    } else if (password.length < 6) {
      setIsError(true);
      setErrorMessage("Password must be at least 6 characters!");
      return;
    }

    const user = { name, surname, email, password };
    MoviesApi.signup(user).then(
      (response) => {
        Auth.userLogin(response.data);
        setEmail("");
        setPassword("");
        setIsLoggedIn(true);
        navigate("/");
        console.log("Signup successful!");
        setIsError(false);
        setErrorMessage("");
      },
      (error) => {
        const resMessage =
          (error.response &&
            error.response.data &&
            error.response.data.message) ||
          error.message ||
          error.toString();
        console.log(resMessage);

        setIsError(true);
        setErrorMessage(resMessage);
      }
    );
  };

  if (isLoggedIn) {
    return <Navigate to="/" />;
  } else {
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
        <div
          style={{
            width: 400,
            height: 400,
            borderRadius: "30%",
            backgroundColor: "rgba(255, 255, 255, 0.9)",
            boxShadow: "0 0 80px #FF4500",
            display: "flex",
            justifyContent: "center",
            alignItems: "center",
          }}
        >
          <Grid textAlign="center">
            <Grid.Column style={{ maxWidth: 450 }}>
              <Form size="large" onSubmit={handleSubmit}>
                <Segment>
                  <h1 style={{ color: "orange" }}>SignUp</h1>
                  <Form.Input
                    fluid
                    autoFocus
                    name="name"
                    icon="user"
                    iconPosition="left"
                    placeholder="Name"
                    value={name}
                    onChange={handleInputChange}
                  />
                  <Grid.Row style={{ paddingBottom: "0.5em" }}></Grid.Row>
                  <Form.Input
                    fluid
                    name="surname"
                    icon="address card"
                    iconPosition="left"
                    placeholder="Surname"
                    value={surname}
                    onChange={handleInputChange}
                  />
                  <Grid.Row style={{ paddingBottom: "0.5em" }}></Grid.Row>
                  <Form.Input
                    fluid
                    name="email"
                    icon="at"
                    iconPosition="left"
                    placeholder="Email"
                    value={email}
                    onChange={handleInputChange}
                  />
                  <Grid.Row style={{ paddingBottom: "0.5em" }}></Grid.Row>

                  <Form.Input
                    fluid
                    name="password"
                    icon="lock"
                    iconPosition="left"
                    placeholder="Password"
                    type="password"
                    value={password}
                    onChange={handleInputChange}
                  />
                  <Grid.Row style={{ paddingBottom: "0.5em" }}></Grid.Row>
                  <Button color="violet" fluid size="large">
                    Signup
                  </Button>
                </Segment>
              </Form>
              <Message>
                {`Already have an account? `}
                <NavLink to="/login" style={{ color: "orange" }}>
                  Log in
                </NavLink>
              </Message>
              {isError && <Message negative>{errorMessage}</Message>}
            </Grid.Column>
          </Grid>
        </div>
      </div>
    );
  }
};

export default Signup;
