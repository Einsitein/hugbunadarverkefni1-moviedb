import React, { useEffect, useState } from "react";
import axios from "axios";

export default function AuthPage() {
  const baseURL = "http://localhost:8080/";
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [signup, setSignup] = useState(true);
  const [endpointURL, setEndpointURL] = useState(null);

  useEffect(() => {
    setEndpointURL(baseURL + (signup ? "user/register" : "user/login"));
  }, [signup]);

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post(endpointURL, {
        email,
        password,
      });
      // login
      if (!signup) {
        const token = response.data;
        if (token.startsWith("Invalid")) {
          alert("Invalid credentials!");
          return;
        }
        console.log(token);
        localStorage.setItem("token", token);
        globalThis.location.href = "/";
      } else if (signup) {
        alert("User registered!");
        setEmail("");
        setPassword("");
        setSignup(false);
      }
    } catch (error) {
      if (error.response.status === 409) {
        alert("User already exists!");
      }
      console.error("There was an unknown error!", error);
    }
  };

  return (
    <div className="auth-container">
      <form className="auth-form" onSubmit={handleSubmit}>
        <h2>{signup ? "Sign Up" : "Log In"}</h2>
        <input
          type="email"
          placeholder="Email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          required
        />
        <input
          type="password"
          placeholder="Password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          required
        />
        <button type="submit">{signup ? "Sign Up" : "Log In"}</button>
      </form>
      <div className="auth-toggle">
        <button onClick={() => setSignup(!signup)}>
          {signup
            ? "Already have an account? Log In"
            : "Don't have an account? Sign Up"}
        </button>
      </div>
    </div>
  );
}
