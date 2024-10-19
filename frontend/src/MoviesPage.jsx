import React, { useState, useEffect } from "react";
import axios from "axios";
import { Link } from "react-router-dom";

export default function MoviesPage() {
  const baseURL = "http://localhost:8080/";
  const [popularMovies, setPopularMovies] = useState([]);
  const [displayingMovies, setDisplayingMovies] = useState([]);
  const [searchString, setSearchString] = useState("");

  useEffect(() => {
    const fetchPopularMovies = async () => {
      try {
        const response = await axios.get(baseURL + "movies");
        setPopularMovies(response.data);
        setDisplayingMovies(response.data);
      } catch (error) {
        console.error("There was an unknown error!", error);
        const dummy_data = [
          {
            id: 1,
            title: "Ice Age",
            image:
              "https://resizing.flixster.com/Vmy5HXvVs9IZI6at1bfKmlozY8E=/206x305/v2/https://resizing.flixster.com/CmeYxRMtsuTB4dPkRlLOBpEUPsA=/ems.cHJkLWVtcy1hc3NldHMvbW92aWVzLzA5NjFmODUzLTdmMDMtNGQyNy1iYzQ5LTA3YTQ5NGEzNDQ4Zi53ZWJw",
          },
          {
            id: 2,
            title: "Ice Age",
            image:
              "https://resizing.flixster.com/Vmy5HXvVs9IZI6at1bfKmlozY8E=/206x305/v2/https://resizing.flixster.com/CmeYxRMtsuTB4dPkRlLOBpEUPsA=/ems.cHJkLWVtcy1hc3NldHMvbW92aWVzLzA5NjFmODUzLTdmMDMtNGQyNy1iYzQ5LTA3YTQ5NGEzNDQ4Zi53ZWJw",
          },
          {
            id: 3,
            title: "Ice Age",
            image:
              "https://resizing.flixster.com/Vmy5HXvVs9IZI6at1bfKmlozY8E=/206x305/v2/https://resizing.flixster.com/CmeYxRMtsuTB4dPkRlLOBpEUPsA=/ems.cHJkLWVtcy1hc3NldHMvbW92aWVzLzA5NjFmODUzLTdmMDMtNGQyNy1iYzQ5LTA3YTQ5NGEzNDQ4Zi53ZWJw",
          },
          {
            id: 4,
            title: "Ice Age",
            image:
              "https://resizing.flixster.com/Vmy5HXvVs9IZI6at1bfKmlozY8E=/206x305/v2/https://resizing.flixster.com/CmeYxRMtsuTB4dPkRlLOBpEUPsA=/ems.cHJkLWVtcy1hc3NldHMvbW92aWVzLzA5NjFmODUzLTdmMDMtNGQyNy1iYzQ5LTA3YTQ5NGEzNDQ4Zi53ZWJw",
          },
          {
            id: 5,
            title: "Ice Age",
            image:
              "https://resizing.flixster.com/Vmy5HXvVs9IZI6at1bfKmlozY8E=/206x305/v2/https://resizing.flixster.com/CmeYxRMtsuTB4dPkRlLOBpEUPsA=/ems.cHJkLWVtcy1hc3NldHMvbW92aWVzLzA5NjFmODUzLTdmMDMtNGQyNy1iYzQ5LTA3YTQ5NGEzNDQ4Zi53ZWJw",
          },
          {
            id: 6,
            title: "Ice Age",
            image:
              "https://resizing.flixster.com/Vmy5HXvVs9IZI6at1bfKmlozY8E=/206x305/v2/https://resizing.flixster.com/CmeYxRMtsuTB4dPkRlLOBpEUPsA=/ems.cHJkLWVtcy1hc3NldHMvbW92aWVzLzA5NjFmODUzLTdmMDMtNGQyNy1iYzQ5LTA3YTQ5NGEzNDQ4Zi53ZWJw",
          },
        ];
        setPopularMovies(dummy_data);
        setDisplayingMovies(dummy_data);
      }
    };
    fetchPopularMovies();
  }, []);

  useEffect(() => {
    if (searchString === "") {
      setDisplayingMovies(popularMovies);
    } else {
      const searchMovies = async () => {
        try {
          const response = await axios.get(
            baseURL + `movies/search?query=${searchString}`
          );
            setDisplayingMovies(response.data);
        } catch (error) {
            setDisplayingMovies([]);
          console.error("There was an unknown error!", error);
        }
      };
      searchMovies();
    }
  }, [searchString]);

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
            <img src={movie.image} alt={movie.title} />
            <p>{movie.title}</p>
          </Link>
        ))}
      </div>
    </div>
  );
}
