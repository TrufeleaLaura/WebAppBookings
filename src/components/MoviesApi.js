import axios from "axios";
import { parseJwt } from "../auth/Helpers";

const API_URL = "http://localhost:8080";
export const MoviesApi = {
  authenticate,
  signup,
  getMovies,
};

function authenticate(email, password) {
  return instance.post(
    "/api/auth/signin",
    { email, password },
    {
      headers: { "Content-type": "application/json" },
    }
  );
}

function signup(user) {
  return instance.post("/api/auth/signup", user, {
    headers: { "Content-type": "application/json" },
  });
}

export function getMovies() {
  return instance.get("/movies/all");
}

// -- Axios

const instance = axios.create({
  baseURL: API_URL,
});

// -- Helper functions

function bearerAuth(user) {
  return `Bearer ${user.accessToken}`;
}
