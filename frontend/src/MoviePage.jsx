import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import axios from "axios";
import { jwtDecode } from "jwt-decode";

export default function MoviePage() {
  const { movieid } = useParams();
  const [movie, setMovie] = useState(null);
  const [avgRating, setAvgRating] = useState("...Nobody has rated this movie yet :(");
  const [myRating, setMyRating] = useState(null);
  const [userid, setUserId] = useState(null);
  const token = localStorage.getItem("token");
  const email = jwtDecode(token).sub;
  console.log(email);
  const baseURL = "http://localhost:8080/";

  useEffect(() => {
    const fetchMovie = async () => {
      try {
        const response = await axios.get(baseURL + `movies/${movieid}`);
        setMovie(response.data);
        console.log(response.data);
      } catch (error) {
        console.error("There was an unknown error!", error);
      }
    };
    const fetchAvgRating = async () => {
      try {
        const response = await axios.get(
          baseURL + `review/findAverageRatingByMovieId/${movieid}`
        );
        console.log(response.data);
        setAvgRating(response.data);
      } catch (error) {
        console.error("There was an unknown error!", error);
      }
    };
    const fetchMyRating = async () => {
      let user_id = null;
      try {
        const user_id_response = await axios.get(baseURL + `users/${email}`);
        user_id = user_id_response.data.id;
        setUserId(user_id);
      } catch (error) {
        console.error("There was an unknown error!", error);
      }
      try {
        if (user_id === null) {
          return;
        }
        const response = await axios.get(
          baseURL + `review/findByUserIdAndMovieId/${user_id}/${movieid}`
        );
        setMyRating(response.data.rating);
      } catch (error) {
        console.error("There was an unknown error!", error);
      }
    };

    fetchMovie();
    fetchAvgRating();
    fetchMyRating();
  }, [movieid, token]);

  const handleDeleteRating = async () => {
    try {
      await axios.delete(baseURL + `review/deleteReview/${userid}-${movieid}`);
      setMyRating(null);
    } catch (error) {
      console.error("Error deleting rating", error);
    }
  };

  const handleChangeRating = async (newRating) => {
    try {
      await axios.patch(baseURL + `review//changeReview`, {
        userid,
        movieid,
        movie_review: "",
        rating: newRating,
      });
      setMyRating(newRating);
    } catch (error) {
      console.error("Error changing rating", error);
    }
  };

  const handleSetRating = async (newRating) => {
    try {
      await axios.post(baseURL + `review/createReview/`, {
        userid,
        movieid,
        movie_review: "",
        rating: newRating,
      });
      setMyRating(newRating);
    } catch (error) {
      console.error("Error setting rating", error);
    }
  };
  if (!movie) {
    return <div>Loading...</div>;
  }
  return (
    <div className="movie-page">
      <div className="movie-page-content">
        <img src={movie.images} alt={movie.title} className="movie-image" />
        <div className="movie-details">
          <h1 className="movie-title">{movie.title}</h1>
          <p>Runtime: {movie.runtime} minutes</p>
          <p>Director: {movie.director}</p>
          <p>Year: {movie.year}</p>
          <p>Average Rating: {avgRating}</p>
          {myRating !== null ? (
            <>
              <p>My Rating: {myRating}</p>
              <button
                onClick={handleDeleteRating}
                className="delete-rating-button"
              >
                Delete Rating
              </button>
              <button
                onClick={() => handleChangeRating(prompt("Enter new rating:"))}
                className="change-rating-button"
              >
                Change Rating
              </button>
            </>
          ) : (
            <button
              onClick={() => handleSetRating(prompt("Enter your rating:"))}
              className="set-rating-button"
            >
              Set Rating
            </button>
          )}
        </div>
      </div>
    </div>
  );
}
