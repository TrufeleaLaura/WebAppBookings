import React from 'react'
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom'
import { AuthProvider} from './auth/AuthContext'
import { NavigationBar } from './components/Navbar';
import Home from './pages/home';
import Login from './auth/Login'
import Signup from './auth/Signup'
import MoviesProgram from './pages/MoviesProgram';
import RegistredUsers from './pages/RegistredUsers';
import Reservations from './pages/Reservations';
import AddMovie from './pages/AddMovie';
import AddMovieProgram from './pages/AddMovieProgram';
import Booking from './pages/Booking';
import UserTickets from './pages/UserTickets';

function App() {
  return (
    <AuthProvider>
      <Router>
        <NavigationBar />
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/login" element={<Login />} />
          <Route path="/signup" element={<Signup />} />
          <Route path="*" element={<Navigate to="/" />} />
          <Route path="/program" element={<MoviesProgram />} />
           <Route path="/registredUsers" element={<RegistredUsers />} /> 
           <Route path="/reservations" element={<Reservations />} />
           <Route path="/addMovie" element={<AddMovie />} />
           <Route path="/addMovieProgram" element={<AddMovieProgram />} />
           <Route path="/booking/:id" element={<Booking/>} />
           <Route path="/userTickets" element={<UserTickets/>} />
        </Routes>
      </Router>
    </AuthProvider>
  );
}

export default App


/* import { useState, useEffect, createContext } from 'react';
import { Route, Routes, Navigate, useLocation } from "react-router-dom";
import * as React from 'react';
import AuthService from './services/auth.service';
import { AuthProvider } from './services/auth-context';
import Home from './components/home.component';
import Login from './components/login.component';
import Register from './components/register.component';
import Profile from './components/profile.component';
import BoardUser from './components/board-user.component';
import BoardAdmin from './components/board-admin.component';
import { NavigationBar } from './newComponents/navBar';
import { useAuthContext } from './services/auth-context';
import { Logout } from './newComponents/Logout';
 */


/* export default function App() {
   const {user}=useAuthContext();
   const location = useLocation();
   //const pathName = location.state?.from?.pathname || '/'
return (
  <div>
    <NavigationBar />
    <Routes>
      <Route path="/" element={<Home />} />
      <Route path='/login' element={<Login />}/>
      <Route path='/signup' element={<Register />}/>
      <Route path="/profile" element={<Profile />} />
      <Route path="/user" element={<BoardUser />} />
      <Route path="/admin" element={<BoardAdmin />} />
      <Route path="/logout" element={<Logout/>} />
    </Routes>

  </div>
)}
   */








