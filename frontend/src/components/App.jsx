import React from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import HomePage from "./HomePage";
import Header from "./Header";
import AuthPage from "./AuthPage";
import LogoutPage from "./LogoutPage";
import MyAccount from "./MyAccount";

export default function App(dict) {
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
