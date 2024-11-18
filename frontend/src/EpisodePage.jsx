import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import axios from "axios";
import { jwtDecode } from "jwt-decode";

export default function EpisodePage() {
  const { tvshowid, seasonid, episodeid } = useParams();
  const [tvshow, setTvShow] = useState(null);
  const [season, setSeason] = useState(null);
  const [episode, setEpisode] = useState(null);
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
    const fetchData = async () => {
      try {
        const showResponse = await axios.get(baseURL + `tvshows/${tvshowid}`);
        setTvShow(showResponse.data);

        const seasonResponse = await axios.get(
          baseURL + `tvshows/${tvshowid}/season/${seasonid}`
        );
        setSeason(seasonResponse.data);

        const episodeResponse = await axios.get(
          baseURL +
            `tvshows/${tvshowid}/season/${seasonid}/episode/${episodeid}`
        );
        setEpisode(episodeResponse.data);

        if (!userid) {
          const user_id = await getUserId();
          try {
            const ratingResponse = await axios.get(
              baseURL + `review/findByUserIdAndMediaId/${user_id}/${episodeid}`
            );
            setMyRating(ratingResponse.data.rating);
          } catch (error) {
            console.log("No rating found for this episode");
          }
        }
      } catch (error) {
        console.error("Error fetching data:", error);
      }
    };

    fetchData();
  }, [tvshowid, seasonid, episodeid, userid]);

  const handleDeleteRating = async () => {
    let user_id = userid;
    if (!userid) {
      user_id = await getUserId();
    }
    try {
      await axios.delete(
        baseURL + `review/deleteReview/${user_id}-${episodeid}`
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
        movieId: episodeid,
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
      await axios.post(baseURL + "review/createReview", {
        userId: `${user_id}`,
        movieId: `${episodeid}`,
        rating: `${newRating}`,
      });
      setMyRating(newRating);
    } catch (error) {
      console.error("Error setting rating", error);
    }
  };

  if (!tvshow || !season || !episode) {
    return <div>Loading...</div>;
  }

  return (
    <div className="episode-page">
      <div className="episode-page-content">
        <img
          src={episode.images || season.images || tvshow.images}
          alt={episode.title}
          className="episode-image"
        />
        <div className="episode-details">
          <h1 className="episode-title">
            {tvshow.name} - S{season.seasonNumber}E{episode.episodeNumber}:{" "}
            {episode.title}
          </h1>
          <p>Episode Name: {episode.episodeName}</p>

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
