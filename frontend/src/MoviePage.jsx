import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import axios from "axios";

export default function MoviePage() {
    const { movieid } = useParams();
    const [movie, setMovie] = useState(null);
    const [avgRating, setAvgRating] = useState(null);
    const [myRating, setMyRating] = useState(null);
    const token = localStorage.getItem("token");
    const baseURL = "http://localhost:8080/";
    const dummy_data = {
        "id": 1,
        "title": "Ice Age",
        "image": "https://resizing.flixster.com/Vmy5HXvVs9IZI6at1bfKmlozY8E=/206x305/v2/https://resizing.flixster.com/CmeYxRMtsuTB4dPkRlLOBpEUPsA=/ems.cHJkLWVtcy1hc3NldHMvbW92aWVzLzA5NjFmODUzLTdmMDMtNGQyNy1iYzQ5LTA3YTQ5NGEzNDQ4Zi53ZWJw",
    }

    useEffect(() => {
        const fetchMovie = async () => {
            try {
                const response = await axios.get(baseURL + `movies/${movieid}`);
                setMovie(response.data);
            } catch (error) {
                console.error("There was an unknown error!", error);
                setMovie(dummy_data);
            }
        };
        const fetchAvgRating = async () => {
            try {
                const response = await axios.get(baseURL + `movies/${movieid}/rating/avg`);
                setAvgRating(response.data);
            } catch (error) {
                console.error("There was an unknown error!", error);
                setAvgRating(5);
            }
        }
        const fetchMyRating = async () => {
            try {
                const response = await axios.get(baseURL + `movies/${movieid}/rating`, {
                    headers: {
                        accessToken: token,
                    },
                });
                setMyRating(response.data.rating);
            } catch (error) {
                console.error("There was an unknown error!", error);
                setMyRating(5);
            }
        }

        fetchMovie();
        fetchAvgRating();
        fetchMyRating();
    }
    , [movieid, token]);


    const handleDeleteRating = async () => {
      try {
        await axios.delete(baseURL + `movies/${movieid}/rating`, {
          headers: {
            accessToken: token,
          },
        });
        setMyRating(null);
      } catch (error) {
        console.error("Error deleting rating", error);
      }
    };

    const handleChangeRating = async (newRating) => {
      try {
        await axios.patch(
          baseURL + `movies/${movieid}/rating`,
          { rating: newRating },
          {
            headers: {
              accessToken: token,
            },
          }
        );
        setMyRating(newRating);
      } catch (error) {
        console.error("Error changing rating", error);
      }
    };

    const handleSetRating = async (newRating) => {
      try {
        await axios.post(
          baseURL + `movies/${movieid}/rating`,
          { rating: newRating },
          {
            headers: {
              accessToken: token,
            },
          }
        );
        setMyRating(newRating);
      } catch (error) {
        console.error("Error setting rating", error);
      }
    };

    
    return (
      <div className="movie-page">
        <div className="movie-page-content">
          <img src={movie.image} alt={movie.title} className="movie-image" />
          <div className="movie-details">
            <h1 className="movie-title">{movie.title}</h1>
            <p>Average Rating: {avgRating}</p>
            {myRating !== null ? (
              <>
                <p>My Rating: {myRating}</p>
                <button onClick={handleDeleteRating} className="delete-rating-button">Delete Rating</button>
                <button
                  onClick={() =>
                    handleChangeRating(prompt("Enter new rating:"))
                  }
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
