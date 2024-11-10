import React, { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import axios from "axios";
import { jwtDecode } from "jwt-decode";

export default function SeasonsPage() {
  const { tvshowid } = useParams();
  const navigate = useNavigate();
  const [tvshow, setTvShow] = useState(null);
  const [seasons, setSeasons] = useState([]);
  const [myRating, setMyRating] = useState(null);
  const [userid, setUserId] = useState(null);
  const token = localStorage.getItem("token");
  const email = jwtDecode(token).sub;
  const baseURL = "http://localhost:8080/";

  const getUserId = async () => {
    const user_id_response = await axios.post(baseURL + "user/idbyemail", {
      email,
    });
    const user_id_tmp = user_id_response.data;
    setUserId(user_id_tmp);
    return user_id_tmp;
  };

  useEffect(() => {
    const fetchTvShow = async () => {
      try {
        const showResponse = await axios.get(baseURL + `tvshows/${tvshowid}`);
        setTvShow(showResponse.data);

        const seasonsResponse = await axios.get(
          baseURL + `tvshows/${tvshowid}/seasons`
        );
        setSeasons(seasonsResponse.data);
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
          baseURL + `review/findByUserIdAndMovieId/${user_id}/${tvshowid}`
        );
        setMyRating(response.data.rating);
      } catch (error) {
        console.error("There was an unknown error!", error);
      }
    };

    fetchTvShow();
    fetchMyRating();
  }, [tvshowid, token]);

  const handleDeleteRating = async () => {
    let user_id = userid;
    if (!userid) {
      user_id = await getUserId();
    }
    try {
      await axios.delete(
        baseURL + `review/deleteReview/${user_id}-${tvshowid}`
      );
      setMyRating(null);
    } catch (error) {
      console.error("Error deleting rating", error);
    }
  };

  const handleChangeRating = async (newRating) => {
    if (!newRating) return;
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
        movieId: tvshowid,
        rating: newRating,
      });
      setMyRating(newRating);
    } catch (error) {
      console.error("Error changing rating", error);
    }
  };

  const handleSetRating = async (newRating) => {
    if (!newRating) return;
    if (newRating < 1 || newRating > 10) {
      alert("Rating must be between 1 and 10!");
      return;
    }
    let user_id = userid;
    if (!userid) {
      user_id = await getUserId();
    }
    try {
      await axios.post(baseURL + "review/createTvShowReview", {
        userId: `${user_id}`,
        movieId: `${tvshowid}`,
        rating: `${newRating}`
      });
      setMyRating(newRating);
    } catch (error) {
      console.error("Error setting rating", error);
    }
  };

  const handleSeasonClick = (seasonId) => {
    navigate(`/tvshows/${tvshowid}/seasons/${seasonId}`);
  };

  if (!tvshow) {
    return <div>Loading...</div>;
  }

  return (
    <div className="tvshow-page-container">
      <div className="tvshow-info">
        <img src={tvshow.images} alt={tvshow.name} className="tvshow-image" />
        <div className="tvshow-details">
          <h1 className="tvshow-title">{tvshow.name}</h1>
          <p>Created By: {tvshow.creators}</p>
          <p>Genre: {tvshow.genre}</p>
          <p>Description: {tvshow.description}</p>

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

      <h2>Seasons</h2>
      <div className="displaying-seasons">
        {seasons.map((season) => (
          <div
            key={season.id}
            className="season-item"
            onClick={() => handleSeasonClick(season.id)}
            style={{ cursor: 'pointer' }}
          >
            <img
              src={season.images || tvshow.images}
              alt={`Season ${season.seasonNumber}`}
            />
            <p>Season {season.seasonNumber}</p>
            <p>{season.airDate}</p>
          </div>
        ))}
      </div>
    </div>
  );
}
