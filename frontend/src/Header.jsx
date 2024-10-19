import React, { useEffect, useState } from "react";
import { jwtDecode } from "jwt-decode";
import reactLogo from "./assets/vite-deno.svg";

export default function Header() {
  const [email, setEmail] = useState(null);

  useEffect(() => {
    const token = localStorage.getItem("token");
    if (token) {
      console.log(token);
      const decoded = jwtDecode(token);
      console.log(decoded);
      const email = decoded.sub;
      setEmail(email);
    }
  }, []);

  return (
    <div className="header">
      <a href="/" className="logo">
        <img src={reactLogo} alt="React Logo" />
      </a>
      <div className="contentButtons">
        {email ? (
          <>
            <a href="/movies">Movies</a>
            <a href="/users">Users</a>
            <a href="/me">My Account</a>
            <a href="/logout">Logout</a>
          </>
        ) : (
          <a href="/auth">Sign up / Login</a>
        )}
      </div>
    </div>
  );
}
