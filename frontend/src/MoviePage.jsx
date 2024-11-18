import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import axios from "axios";
import { jwtDecode } from "jwt-decode";

export default function MoviePage() {
  const { movieid } = useParams();
  const [movie, setMovie] = useState(null);
  const [avgRating, setAvgRating] = useState(
    "...Nobody has rated this movie yet :("
  );
  const [myRating, setMyRating] = useState(null);
  const [userid, setUserId] = useState(null);
  const token = localStorage.getItem("token");
  const email = jwtDecode(token).sub;
  console.log(email);
  const baseURL = "http://localhost:8080/";

  const getUserId = async () => {
    const user_id_response = await axios.post(baseURL + "user/idbyemail", {
      email,
    });
    const user_id_tmp = user_id_response.data;
    setUserId(user_id_tmp);
    return user_id_tmp;
  };

  const fetchAvgRating = async () => {
    try {
      const response = await axios.get(
        baseURL + `review/findAverageRatingByMediaId/${movieid}`
      );
      console.log(response.data);
      setAvgRating(response.data);
    } catch (error) {
      console.error("There was an unknown error!", error);
    }
  };

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

    const fetchMyRating = async () => {
      let user_id = userid;
      if (!userid) {
        user_id = await getUserId();
      }
      try {
        const response = await axios.get(
          baseURL + `review/findByUserIdAndMediaId/${user_id}/${movieid}`
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
    let user_id = userid;
    if (!userid) {
      user_id = await getUserId();
    }
    try {
      await axios.delete(baseURL + `review/deleteReview/${user_id}-${movieid}`);
      setMyRating(null);
      await fetchAvgRating();
    } catch (error) {
      console.error("Error deleting rating", error);
    }
  };

  const handleChangeRating = async (newRating) => {
    if (!newRating) {
      return;
    }
    if (newRating < 1 || newRating > 10) {
      alert("Rating must be between 1 and 10!");
      return;
    }
    let user_id = userid;
    if (!userid) {
      user_id = await getUserId();
    }
    try {
      await axios.patch(baseURL + `review/changeReview`, {
        userId: user_id,
        movieId: movieid,
        movieReview: "",
        rating: newRating,
      });
      setMyRating(newRating);
      await fetchAvgRating();
    } catch (error) {
      console.error("Error changing rating", error);
    }
  };

  const handleSetRating = async (newRating) => {
    if (!newRating) {
      return;
    }
    if (newRating < 1 || newRating > 10) {
      alert("Rating must be between 1 and 10!");
      return;
    }
    let user_id = userid;
    if (!userid) {
      user_id = await getUserId();
    }
    try {
      await axios.post(baseURL + "review/createReview", {
        userId: `${user_id}`,
        movieId: `${movieid}`,
        movieReview: "",
        rating: `${newRating}`,
      });
      setMyRating(newRating);
      await fetchAvgRating();
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
