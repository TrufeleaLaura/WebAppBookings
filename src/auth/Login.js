import React, { useContext, useState, useEffect } from "react";
import { NavLink, Navigate, useNavigate } from "react-router-dom";
import { Button, Form, Grid, Segment, Message } from "semantic-ui-react";
import AuthContext from "./AuthContext";
import { MoviesApi } from "../components/MoviesApi";
import { parseJwt, handleLogError } from "./Helpers";
import { FormGroup } from "react-bootstrap";

export default function Login() {
  const Auth = useContext(AuthContext);
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [isError, setIsError] = useState(false);
  const [errorMessage, setErrorMessage] = useState("");
  const navigate = useNavigate();

  useEffect(() => {
    const isLoggedIn = Auth.userIsAuthenticated();
    setIsLoggedIn(isLoggedIn);
  }, [Auth]);

  const handleInputChange = (e, { name, value }) => {
    if (name === "email") {
      setEmail(value);
    } else if (name === "password") {
      setPassword(value);
    }
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    if (!(email && password)) {
      setIsError(true);
      return;
    }

    MoviesApi.authenticate(email, password)
      .then((response) => {
        if (response.data.accessToken) {
          Auth.userLogin(response.data);
          navigate("/");
          //window.location.reload();
          setIsLoggedIn(true);
          setEmail("");
          setPassword("");
          setIsError(false);
        }
      },(error) => {
        const resMessage =
          (error.response &&
            error.response.data &&
            error.response.data.message) ||
          error.message ||
          error.toString();
        //console.log(resMessage);

        setIsError(true);
        setErrorMessage(resMessage);
      }
    );
      /*)
      .catch((error) => {
        handleLogError(error);
        setIsError(true);
      });*/
  };
  if (isLoggedIn) return <Navigate to="/movies" />;
  else {
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
                  <h1 style={{ color: "orange" }}>Login</h1>
                  <Form.Input
                    fluid
                    autoFocus
                    name="email"
                    icon="user"
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
                  <Button
                    color='orange'
                    fluid
                    size="large"
                    onClick={handleSubmit}
                  >
                    Login
                  </Button>
                </Segment>
              </Form>
              <Message>
                {`Don't have already an account? `}
                <NavLink to="/signup" style={{ color: "orange" }}>
                  Sign Up
                </NavLink>
              </Message>
              {isError && (
                <Message negative style={{ color: "red" }}>
                  {errorMessage}
                </Message>
              )}
            </Grid.Column>
          </Grid>
        </div>
      </div>
    );
  }
}
