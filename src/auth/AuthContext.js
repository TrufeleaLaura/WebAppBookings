import React, { useContext, useState, useEffect } from "react";

const AuthContext = React.createContext();

export function useAuth() {
  return useContext(AuthContext);
}

export function AuthProvider({ children }) {
  const [user, setUser] = useState(null);

  useEffect(() => {
    const storedUser = localStorage.getItem("user");
    if (storedUser) {
      setUser(JSON.parse(storedUser));
    }
  }, []);

  const getUser = () => {
    const storedUser = localStorage.getItem("user");
    return storedUser ? JSON.parse(storedUser) : null;
  };

  const userIsAuthenticated = () => {
    const storedUser = localStorage.getItem("user");
    //console.log(storedUser);
    if (!storedUser) {
      return false;
    }
    return true;
  };

  const userLogin = (user) => {
    localStorage.setItem("user", JSON.stringify(user));
    setUser(user);
  };

  const userLogout = () => {
    localStorage.removeItem("user");
    setUser(null);
  };

  return (
    <AuthContext.Provider
      value={{ user, getUser, userIsAuthenticated, userLogin, userLogout }}
    >
      {children}
    </AuthContext.Provider>
  );
}
export default AuthContext;
