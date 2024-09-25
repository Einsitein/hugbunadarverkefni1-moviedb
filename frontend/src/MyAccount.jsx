import React, { useEffect, useState } from "react";
import { jwtDecode } from "jwt-decode";
import axios from "axios";

export default function MyAccount() {
  const token = localStorage.getItem("token");
  const [newPassword, setNewPassword] = useState("");
  const [newPasswordAgain, setNewPasswordAgain] = useState("");
  const baseURL = "http://localhost:8080/";
  const [passwordSameMessage, setPasswordSameMessage] = useState("");

  useEffect(() => {
    if (newPassword === newPasswordAgain) {
      setPasswordSameMessage("Passwords match!");
    } else {
      setPasswordSameMessage("Passwords do not match!");
    }
    // length check
    if (newPassword.length < 5) {
      setPasswordSameMessage("Password must be at least 5 characters!");
    }
  }, [newPassword, newPasswordAgain]);

  const handleChangePassword = async (e) => {
    e.preventDefault();
    console.log(passwordSameMessage);
    if (passwordSameMessage === "Passwords do not match!") {
      alert("Passwords do not match!");
      return;
    }
    if (passwordSameMessage === "Password must be at least 5 characters!") {
      alert("Password must be at least 5 characters!");
      return;
    }
    try {
      const response = await axios.patch(baseURL + "user/password", {
        accessToken: token,
        newPassword: newPassword,
      });
      alert("Password changed!");
    } catch (error) {
      console.error("There was an unknown error!", error);
    }
  };

  return (
    <div className="auth-container">
      <form className="auth-form" onSubmit={handleChangePassword}>
        <h2>Change Password</h2>
        <div>
          <label>New Password:</label>
          <input
            type="password"
            value={newPassword}
            onChange={(e) => setNewPassword(e.target.value)}
            required
          />
        </div>
        <div>
          <label>New Password Again:</label>
          <input
            type="password"
            value={newPasswordAgain}
            onChange={(e) => setNewPasswordAgain(e.target.value)}
            required
          />
        </div>
        <button type="submit">Change Password</button>
      </form>
      {passwordSameMessage !== "Passwords match!" && (
        <div className="error-message">{passwordSameMessage}</div>
      )}
    </div>
  );
}
