import React, { useEffect, useState } from "react";
import axios from "axios";
import { jwtDecode } from "jwt-decode";

export default function MyAccount() {
  const token = localStorage.getItem("token");
  const email = jwtDecode(token).sub;
  const [myEmail, setMyEmail] = useState("");
  const [myMovieRatings, setMyMovieRatings] = useState([]);
  const [myTVRatings, setMyTVRatings] = useState([]);
  const [newPassword, setNewPassword] = useState("");
  const [newPasswordAgain, setNewPasswordAgain] = useState("");
  const baseURL = "http://localhost:8080/";
  const [passwordSameMessage, setPasswordSameMessage] = useState("");
  const [userId, setUserId] = useState(null);
  const [averageRating, setAverageRating] = useState(null);

  const getUserId = async () => {
    const user_id_response = await axios.post(baseURL + "user/idbyemail", {
      email,
    });
    const user_id_tmp = user_id_response.data;
    setUserId(user_id_tmp);
    return user_id_tmp;
  };

  const fetchMyMovieRatings = async () => {
    let user_id = userId;
    if (!userId) {
      user_id = await getUserId();
    }
    try {
      const response = await axios.get(
        baseURL + `user/users/${user_id}/movies/ratings`
      );
      const movieRatings = await Promise.all(
        response.data.map(async (rating_obj) => {
          console.log(rating_obj);
          try {
            const movieResponse = await axios.get(
              baseURL + `movies/${rating_obj.movieId}`
            );
            return {
              myRating: rating_obj.rating,
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
      setMyMovieRatings(filteredMovieRatings);
    } catch (error) {
      console.error("There was an unknown error!", error);
      console.log("hereeeeeeeeeeeeeeeeeee");
    }
  };


    const fetchMyTVRatings = async () => {
      let user_id = userId;
      if (!userId) {
        user_id = await getUserId();
      }
      try {
        const response = await axios.get(
          baseURL + `user/me/ratings/tvshows/${user_id}`, {
            headers: {
              Authorization: `${token}`,
            },
          }
        );
        const tvRatings = await Promise.all(
          response.data.map(async (rating_obj) => {
            try {
              const tvResponse = await axios.get(
                baseURL + `tvshows/${rating_obj.movieId}`
              );
              return {
                images: tvResponse.data.images,
                name: tvResponse.data.name,
                myRating: rating_obj.rating,
                ...tvResponse.data,
              };
            } catch (error) {
              console.error("There was an unknown error!", error);
              return null;
            }
          }
        )
        );
        console.log("tvRatings", tvRatings);
        setMyTVRatings(tvRatings);


      } catch (error) {
        console.error("ifnufnfiunfifnifnijf", error);
      }
    };

  const fetchAverageRating = async () => {
    let user_id = userId;
    if (!userId) {
      user_id = await getUserId();
    }
    try {
      const response = await axios.get(
        baseURL + `user/users/${user_id}/movies/ratings/avg`
      );
      setAverageRating(response.data);
    } catch (error) {
      console.error("Error fetching average rating:", error);
    }
  };

  useEffect(() => {
    const fetchMyEmail = async () => {
      try {
        const response = await axios.get(baseURL + "user/me", {
          headers: {
            Authorization: `${token}`,
          },
        });
        setMyEmail(response.data.email);
      } catch (error) {
        console.error("There was an unknown error!", error);
      }
    };
    fetchMyEmail();
    fetchMyTVRatings();
    fetchMyMovieRatings();
    fetchAverageRating();
  }, [token]);

  useEffect(() => {
    if (newPassword === newPasswordAgain) {
      setPasswordSameMessage("Passwords match!");
    } else {
      setPasswordSameMessage("Passwords do not match!");
    }
    // length check
    if (newPassword.length < 5) {
      setPasswordSameMessage("Password must be at least 5 characters!");
    }
  }, [newPassword, newPasswordAgain]);

  const handleChangePassword = async (e) => {
    e.preventDefault();
    console.log(passwordSameMessage);
    if (passwordSameMessage === "Passwords do not match!") {
      alert("Passwords do not match!");
      return;
    }
    if (passwordSameMessage === "Password must be at least 5 characters!") {
      alert("Password must be at least 5 characters!");
      return;
    }
    try {
      const response = await axios.patch(baseURL + "user/password", {
        accessToken: token,
        newPassword: newPassword,
      });
      alert("Password changed!");
    } catch (error) {
      console.error("There was an unknown error!", error);
    }
  };

  const handleDeleteAccount = async () => {
    if (
      globalThis.confirm(
        "Are you sure you want to delete your account? This action cannot be undone."
      )
    ) {
      try {
        await axios.delete(baseURL+"user/me", {
          headers: {
            Authorization: `${token}`,
          },
        });
        alert("Account deleted!");
        localStorage.removeItem("token");
        globalThis.location.href = "/";
      } catch (error) {
        console.error("There was an unknown error!", error);
      }
    }
  };

  const handleDeleteRatings = async () => {
    if (
      globalThis.confirm(
        "Are you sure you want to delete all ratings for everybody? This action cannot be undone."
      )
    ) {
      try {
        await axios.delete(baseURL+"review/deleteAll");
        await fetchMyMovieRatings();
        alert("All Ratings deleted!");
      } catch (error) {
        console.error("There was an unknown error!", error);
      }
    }
  };

  return (
    <div className="auth-container">
      <h1>My Account</h1>
      <div>
        <label>Email:</label>
        <div>{myEmail}</div>
      </div>
      <div className="movie-ratings">
        <h2>My Ratings</h2>
        {averageRating !== null && typeof averageRating === "number" ? (
          <div className="average-rating">
            Average Rating: {averageRating.toFixed(1)}
          </div>
        ) : (
          <div className="average-rating">No ratings yet</div>
        )}
        <h2>My Movie Ratings</h2>
        {myMovieRatings.map((movie, index) => (
          <div key={index} className="movie-rating">
            <img src={movie.images} alt={movie.title} />
            <div>
              <strong>{movie.name}</strong>
            </div>
            <div>Rating: {movie.myRating}</div>
          </div>
        ))}
        <h2>My TV Ratings</h2>
        {myTVRatings.map((tv, index) => (
          <div key={index} className="movie-rating">
            <img src={tv.images} alt={tv.name} />
            <div>
              <strong>{tv.name}</strong>
            </div>
            <div>Rating: {tv.myRating}</div>
          </div>
        ))}
        {email === "einar@gmail.com" && (
          <button
            onClick={handleDeleteRatings}
            className="delete-my-ratings-button"
          >
            Delete All Ratings for Everybody
          </button>
        )}
      </div>
      <form className="auth-form" onSubmit={handleChangePassword}>
        <h2>Change Password</h2>
        <div>
          <label>New Password:</label>
          <input
            type="password"
            value={newPassword}
            onChange={(e) => setNewPassword(e.target.value)}
            required
          />
        </div>
        <div>
          <label>New Password Again:</label>
          <input
            type="password"
            value={newPasswordAgain}
            onChange={(e) => setNewPasswordAgain(e.target.value)}
            required
          />
        </div>
        <button type="submit">Change Password</button>
      </form>
      {passwordSameMessage !== "Passwords match!" && (
        <div className="error-message">{passwordSameMessage}</div>
      )}
      <button onClick={handleDeleteAccount} className="delete-account-button">
        Delete Account
      </button>
    </div>
  );
}
