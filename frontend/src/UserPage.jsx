import React, { useEffect, useState } from "react";
import axios from "axios";
import { useParams } from "react-router-dom";


export default function UserPage() {
  const { userid } = useParams();
  const baseURL = "http://localhost:8080/";
  const [movieRatings, setMovieRatings] = useState([]);
  const [averageRating, setAverageRating] = useState(null);

  const fetchMovieRatings = async () => {
    try {
      const response = await axios.get(
        baseURL + `user/users/${userid}/movies/ratings`
      );
      const movieRatings = await Promise.all(
        response.data.map(async (rating_obj) => {
          console.log(rating_obj);
          try {
            const movieResponse = await axios.get(
              baseURL + `movies/${rating_obj.movieId}`
            );
            return {
              userRating: rating_obj.rating,
              ...movieResponse.data,
            };
          } catch (error) {
            console.error("There was an unknown error!", error);
            return null;
          }
        })
      );
      const filteredMovieRatings = movieRatings.filter(rating => rating !== null);
      console.log(filteredMovieRatings);
      setMovieRatings(filteredMovieRatings);
    } catch (error) {
      console.error("There was an unknown error!", error);
      console.log("hereeeeeeeeeeeeeeeeeee");
    }
  };

  const fetchAverageRating = async () => {
    try {
      const response = await axios.get(
        baseURL + `user/users/${userid}/movies/ratings/avg`
      );
      setAverageRating(response.data);
    } catch (error) {
      console.error("Error fetching average rating:", error);
    }
  };

  useEffect(() => {
    fetchMovieRatings();
    fetchAverageRating();
  }, []);

  return (
    <div className="user-ratings-container">
      <h1> Account</h1>
      <div className="movie-ratings">
        <h2>Movie Ratings</h2>
        {averageRating !== null && typeof averageRating === 'number' ? (
          <div className="average-rating">
            Average Rating: {averageRating.toFixed(1)}
          </div>
        ) : (
          <div className="average-rating">
            No ratings yet
          </div>
        )}
        {movieRatings.map((movie, index) => (
          <div key={index} className="movie-rating">
            <img src={movie.images} alt={movie.name} />
            <div>
              <strong>{movie.title}</strong>
            </div>
            <div>Rating: {movie.userRating}</div>
          </div>
        ))}
      </div>
    </div>
  );
}
