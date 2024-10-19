import React, { useState, useEffect } from "react";
import axios from "axios";
import { Link } from "react-router-dom";

export default function MoviesPage() {
  const baseURL = "http://localhost:8080/";
  const [popularMovies, setPopularMovies] = useState([]);
  const [searchedMovies, setSearchedMovies] = useState([]);
  const [searchString, setSearchString] = useState("");

  useEffect(() => {
    const fetchPopularMovies = async () => {
      try {
        const response = await axios.get(baseURL + "movies");
        setPopularMovies(response.data);
      } catch (error) {
        console.error("There was an unknown error!", error);
      }
    };
    fetchPopularMovies();
  }, []);

  useEffect(() => {
    const searchMovies = async () => {
      try {
        const response = await axios.get(
          baseURL + `movies/search/${searchString}`
        );
        setSearchedMovies(response.data);
      } catch (error) {
        console.error("There was an unknown error!", error);
      }
    };
    if (searchString !== "") {
      searchMovies();
    }
  }, [searchString]);

  const displayingMovies = searchString === "" ? popularMovies : searchedMovies;

  return (
    <div className="movies-page">
      <h1>Movies Page</h1>
      <input
        type="text"
        placeholder="Search for a movie..."
        value={searchString}
        onChange={(e) => setSearchString(e.target.value)}
        className="search-input"
      />
      <div className="displaying-movies">
        {displayingMovies.map((movie, index) => (
          <Link to={`/movies/${movie.id}`} key={index} className="movie-item">
            <img src={movie.images} alt={movie.name} />
            <p>{movie.name}</p>
          </Link>
        ))}
      </div>
    </div>
  );
}
