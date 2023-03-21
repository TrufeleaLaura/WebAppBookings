import React from 'react'
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom'
import { AuthProvider} from './auth/AuthContext'
import { NavigationBar } from './components/Navbar';
import Home from './pages/home.component';
import Login from './auth/Login'
import Signup from './auth/Signup'

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








