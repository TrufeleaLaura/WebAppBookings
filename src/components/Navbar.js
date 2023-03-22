import { useState, useEffect, createContext } from "react";
import { Link, useNavigate } from "react-router-dom";
import * as React from "react";
import AppBar from "@mui/material/AppBar";
import Box from "@mui/material/Box";
import Toolbar from "@mui/material/Toolbar";
import IconButton from "@mui/material/IconButton";
import Typography from "@mui/material/Typography";
import Menu from "@mui/material/Menu";
import MenuIcon from "@mui/icons-material/Menu";
import Container from "@mui/material/Container";
import Button from "@mui/material/Button";
import MenuItem from "@mui/material/MenuItem";
import MovieIcon from "@mui/icons-material/Movie";
import "bootstrap/dist/css/bootstrap.min.css";
import { useAuth } from "../auth/AuthContext";

function NavigationBar() {
  const { getUser, userIsAuthenticated, userLogout } = useAuth();
  const logout = () => {
    userLogout();
    navigate("/");
    //window.location.reload();
  };
  const navigate = useNavigate();
  const userPages = ["My Tickets"];
  const adminPages = ["Movies Program", "Registred Users", "Reservations"];
  const homeSettings = ["Login", "Sign Up"];
  const loggedSettings = ["Logout"];

  const links = {
    Home: "/",
    Login: "/login",
    "Sign Up": "/signup",
    MyTickets: "/tickets",
    MoviesProgram: "/movies",
    "Registred Users": "/users",
    Reservations: "/reservations",
  };

  const [pages, setPages] = useState([]);
  const [settings, setSettings] = useState([]);
  const useAu=userIsAuthenticated();

  useEffect(() => {
    if (userIsAuthenticated()) {
      const user = getUser();
      console.log(user);
      if (user.role === "Manager") {
        setPages(adminPages);
        setSettings(loggedSettings);
      } else {
        setPages(userPages);
        setSettings(loggedSettings);
      }
    } else {
      setPages([]);
      setSettings(homeSettings);
    }
  }, [useAu]);

  const [anchorElNav, setAnchorElNav] = React.useState(null);

  const handleOpenNavMenu = (event) => {
    setAnchorElNav(event.currentTarget);
  };

  const handleCloseNavMenu = () => {
    setAnchorElNav(null);
  };

  return (
    <AppBar position="static" sx={{background:"#e54304"}}>
      <Container maxWidth="xl">
        <Toolbar disableGutters>
          <MovieIcon sx={{ display: { xs: "none", md: "flex" }, mr: 1 }} />
          <Typography
            variant="h5"
            noWrap
            component="a"
            href="/"
            sx={{
              mr: 2,
              display: { xs: "none", md: "flex" },
              fontFamily: "monospace",
              fontWeight: 1000,
              letterSpacing: ".2rem",
              color: "inherit",
              textDecoration: "none",
              transition: "background 1s, color 1s",
              "&:hover": {
                backgroundColor: "rgba(0, 0, 0, 0.5)",
                color: "white",
                textDecoration: "none",
              },
            }}
          >
            TRANSYLVANIA CINEMA
          </Typography>
          {pages.length > 0 && (
            <Box sx={{ flexGrow: 1, display: { xs: "flex", md: "none" } }}>
              <IconButton
                size="large"
                aria-label="account of current user"
                aria-controls="menu-appbar"
                aria-haspopup="true"
                onClick={handleOpenNavMenu}
                color="inherit"
              >
                <MenuIcon />
              </IconButton>
              <Menu
                id="menu-appbar"
                anchorEl={anchorElNav}
                anchorOrigin={{
                  vertical: "bottom",
                  horizontal: "left",
                }}
                keepMounted
                transformOrigin={{
                  vertical: "top",
                  horizontal: "left",
                }}
                open={Boolean(anchorElNav)}
                onClose={handleCloseNavMenu}
                sx={{
                  display: { xs: "block", md: "none" },
                }}
              >
                {pages.map((page) => (
                  <MenuItem
                    sx={{
                      transition: "background 1s, color 1s",
                      "&:hover": {
                        backgroundColor: "rgba(0, 0, 0, 0.5)",
                        color: "white",
                        textDecoration: "none",
                      },
                    }}
                    key={page}
                    onClick={handleCloseNavMenu}
                  >
                    <Link to={links[page]}>
                      <Typography textAlign="center">{page}</Typography>
                    </Link>
                  </MenuItem>
                ))}
              </Menu>
            </Box>
          )}

          <MovieIcon sx={{ display: { xs: "flex", md: "none" }, mr: 1 }} />
          <Typography
            variant="h5"
            noWrap
            component="a"
            href=""
            sx={{
              mr: 2,
              display: { xs: "flex", md: "none" },
              flexGrow: 1,
              fontFamily: "monospace",
              fontWeight: 700,
              letterSpacing: ".3rem",
              color: "inherit",
              textDecoration: "none",
            }}
          >
            TRANSYLVANIA CINEMA
          </Typography>

          <Box sx={{ flexGrow: 1, display: { xs: "none", md: "flex" } }}>
            {pages.map((page) => {
              return (
                <Button
                  key={page}
                  onClick={handleCloseNavMenu}
                  component={Link}
                  to={links[page]}
                  sx={{
                    my: 2,
                    color: "white",
                    display: "block",
                    transition: "background 1s, color 1s",
                    "&:hover": {
                      backgroundColor: "rgba(0, 0, 0, 0.5)",
                      color: "white",
                      textDecoration: "none",
                    },
                  }}
                >
                  {page}
                </Button>
              );
            })}
          </Box>
          <Box
            sx={{ flexGrow: 1, display: "flex", justifyContent: "flex-end" }}
          >
            {settings.map((setting) => {
              if (setting === "Logout") {
                return (
                  <Button
                    key={setting}
                    onClick={logout}
                    sx={{
                      my: 2,
                      color: "white",
                      display: "block",
                      transition: "background 1s, color 1s",
                      "&:hover": {
                        backgroundColor: "rgba(0, 0, 0, 0.5)",
                        color: "white",
                        textDecoration: "none",
                      },
                    }}
                  >
                    {setting}
                  </Button>
                );
              } else {
                return (
                  <Button
                    key={setting}
                    component={Link}
                    to={links[setting]}
                    sx={{
                      my: 2,
                      color: "white",
                      display: "block",
                      transition: "background 1s, color 1s",
                      "&:hover": {
                        backgroundColor: "rgba(0, 0, 0, 0.5)",
                        color: "white",
                        textDecoration: "none",
                      },
                    }}
                  >
                    {setting}
                  </Button>
                );
              }
            })}
          </Box>
        </Toolbar>
      </Container>
    </AppBar>
  );
}
export { NavigationBar };
