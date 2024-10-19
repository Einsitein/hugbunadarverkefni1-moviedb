import React, { useEffect, useState } from "react";
import axios from "axios";
import { useParams } from "react-router-dom";


export default function UserPage() {
  const { userid } = useParams();
  const baseURL = "http://localhost:8080/";
  const [userEmail, setUserEmail] = useState("");
  const [movieRatings, setMovieRatings] = useState([
    {
      title: "Ice Age",
      rating: 5,
      image:
        "https://resizing.flixster.com/Vmy5HXvVs9IZI6at1bfKmlozY8E=/206x305/v2/https://resizing.flixster.com/CmeYxRMtsuTB4dPkRlLOBpEUPsA=/ems.cHJkLWVtcy1hc3NldHMvbW92aWVzLzA5NjFmODUzLTdmMDMtNGQyNy1iYzQ5LTA3YTQ5NGEzNDQ4Zi53ZWJw",
    },
    {
      title: "Ice Age",
      rating: 5,
      image:
        "https://resizing.flixster.com/Vmy5HXvVs9IZI6at1bfKmlozY8E=/206x305/v2/https://resizing.flixster.com/CmeYxRMtsuTB4dPkRlLOBpEUPsA=/ems.cHJkLWVtcy1hc3NldHMvbW92aWVzLzA5NjFmODUzLTdmMDMtNGQyNy1iYzQ5LTA3YTQ5NGEzNDQ4Zi53ZWJw",
    },
  ]);

  useEffect(() => {
    const fetchUserRatings = async () => {
      try {
        const response = await axios.get(baseURL+`users/${userid}/movies/ratings`);
        const data = await response.data;
        setUserEmail(data.email);
        setMovieRatings(data.ratings);
      } catch (error) {
        console.error("There was an unknown error!", error);
      }
    };
    fetchUserRatings();
  }, []);


   return (
     <div className="user-ratings-container">
       <h1> Account</h1>
       <div>
         <label>Email:</label>
         <div>{userEmail}</div>
       </div>
       <div className="movie-ratings">
         <h2>Movie Ratings</h2>
         {movieRatings.map((movie, index) => (
           <div key={index} className="movie-rating">
             <img src={movie.image} alt={movie.title} />
             <div>
               <strong>{movie.title}</strong>
             </div>
             <div>Rating: {movie.rating}</div>
           </div>
         ))}
       </div>
     </div>
   );
}
