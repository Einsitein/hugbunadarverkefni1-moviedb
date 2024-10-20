import React, { useState, useEffect } from "react";
import axios from "axios";

const UsersPage = () => {
  const [users, setUsers] = useState([]);
  const baseURL = "http://localhost:8080/";

  useEffect(() => {
    async function fetchUsers() {
      try {
        const response = await axios.get(baseURL + "user/users");
        const data = await response.data;
        console.log(data);
        setUsers(data);
      } catch (error) {
        console.error("There was an unknown error!", error);
      }
    }
    fetchUsers();
  }, []);

  const handleUserClick = (userId) => {
    globalThis.history.pushState(null, "", `/users/${userId}`);
    globalThis.dispatchEvent(new PopStateEvent("popstate"));
  };

  return (
    <div className="users-page">
      <h1 className="users-page-title">Users Page</h1>
      <ul className="users-list">
        {users.map((user) => (
          <li
            key={user.id}
            className="user-item"
            onClick={() => handleUserClick(user.id)}
          >
            {user.email}
          </li>
        ))}
      </ul>
    </div>
  );
};

export default UsersPage;
