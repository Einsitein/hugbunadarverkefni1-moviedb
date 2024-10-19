import React, { useState, useEffect } from "react";
import axios from "axios";

const UsersPage = () => {
  const [users, setUsers] = useState([
    {
      id: 1,
      email: "abc@gmail.com",
    },
    {
      id: 2,
      email: "def@gmail.com",
    },
  ]);

  useEffect(() => {
    async function fetchUsers() {
      try {
        const response = await axios.get(baseURL + "users");
        const data = await response.data;
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
