import React from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import HomePage from "./HomePage.jsx";
import Header from "./Header.jsx";
import AuthPage from "./AuthPage.jsx";
import LogoutPage from "./LogoutPage.jsx";
import MyAccount from "./MyAccount.jsx";

export default function App() {
  return (
    <BrowserRouter>
      <Header />
      <Routes>
        <Route path="/">
          <Route index element={<HomePage />} />
          <Route exact path="auth" element={<AuthPage />} />
          <Route exact path="logout" element={<LogoutPage />} />
          <Route exact path="me" element={<MyAccount />} />
        </Route>
      </Routes>
    </BrowserRouter>
  );
}
