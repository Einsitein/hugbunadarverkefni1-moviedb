import React from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import HomePage from "./HomePage.jsx";
import Header from "./Header.jsx";
import AuthPage from "./AuthPage.jsx";
import LogoutPage from "./LogoutPage.jsx";
import MyAccount from "./MyAccount.jsx";
import MoviesPage from "./MoviesPage.jsx";
import MoviePage from "./MoviePage.jsx";
import UsersPage from "./UsersPage.jsx";
import UserPage from "./UserPage.jsx";
import TVShowsPage from "./TVShowsPage.jsx";
import SeasonsPage from "./SeasonsPage.jsx";
import EpisodesPage from "./EpisodesPage.jsx";
import EpisodePage from "./EpisodePage.jsx";

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
          <Route exact path="movies" element={<MoviesPage />} />
          <Route exact path="movies/:movieid" element={<MoviePage />} />
          <Route exact path="users" element={<UsersPage />} />
          <Route exact path="users/:userid" element={<UserPage />} />
          <Route exact path="tvshows" element={<TVShowsPage />} />
          <Route exact path="tvshows/:tvshowid" element={<SeasonsPage />} />
          <Route exact path="tvshows/:tvshowid/seasons/:seasonid" element={<EpisodesPage />} />
          <Route exact path="tvshows/:tvshowid/seasons/:seasonid/episodes/:episodeid" element={<EpisodePage />} />
        </Route>
      </Routes>
    </BrowserRouter>
  );
}
