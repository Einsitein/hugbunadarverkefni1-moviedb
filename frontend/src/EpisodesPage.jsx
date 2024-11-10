import React, { useEffect, useState } from "react";
import { useParams, Link } from "react-router-dom";
import axios from "axios";
import { jwtDecode } from "jwt-decode";

export default function EpisodesPage() {
  const { tvshowid, seasonid } = useParams();
  const [tvshow, setTvShow] = useState(null);
  const [season, setSeason] = useState(null);
  const [episodes, setEpisodes] = useState([]);
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
        // Fetch TV show data
        const showResponse = await axios.get(baseURL + `tvshows/${tvshowid}`);
        setTvShow(showResponse.data);

        // Fetch season data
        const seasonResponse = await axios.get(
          baseURL + `tvshows/${tvshowid}/season/${seasonid}`
        );
        setSeason(seasonResponse.data);

        // Fetch episodes data
        const episodesResponse = await axios.get(
          baseURL + `tvshows/${tvshowid}/season/${seasonid}/episodes`
        );
        setEpisodes(episodesResponse.data);

        // Fetch user's rating if exists
        if (!userid) {
          const user_id = await getUserId();
          const ratingResponse = await axios.get(
            baseURL + `review/findByUserIdAndMovieId/${user_id}/${seasonid}`
          );
          setMyRating(ratingResponse.data.rating);
        }
      } catch (error) {
        console.error("Error fetching data:", error);
      }
    };

    fetchData();
  }, [tvshowid, seasonid, userid]);

  const handleDeleteRating = async () => {
    let user_id = userid;
    if (!userid) {
      user_id = await getUserId();
    }
    try {
      await axios.delete(
        baseURL + `review/deleteReview/${user_id}-${seasonid}`
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
        movieId: seasonid,
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
      await axios.post(baseURL + "review/createSeasonReview", {
        userId: `${user_id}`,
        movieId: `${seasonid}`,
        rating: `${newRating}`
      });
      setMyRating(newRating);
    } catch (error) {
      console.error("Error setting rating", error);
    }
  };

  if (!tvshow || !season) {
    return <div>Loading...</div>;
  }

  return (
    <div className="tvshow-page-container">
      <div className="tvshow-info">
        <img src={season.images || tvshow.images} alt={season.name} className="tvshow-image" />
        <div className="tvshow-details">
          <h1 className="tvshow-title">{tvshow.name} - Season {season.seasonNumber}</h1>
          <p>Description: {season.tvShow?.description}</p>

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

      <h2>Episodes</h2>
      <div className="displaying-seasons">
        {episodes.map((episode) => (
          <Link 
            to={`/tvshows/${tvshowid}/seasons/${seasonid}/episodes/${episode.id}`}
            key={episode.id} 
            className="season-item"
          >
            <img
              src={episode.images || season.images || tvshow.images}
              alt={`Episode ${episode.episodeNumber}`}
            />
            <p>Episode {episode.episodeNumber}</p>
            <p>{episode.title}</p>
            <p>{episode.airDate}</p>
          </Link>
        ))}
      </div>
    </div>
  );
}
