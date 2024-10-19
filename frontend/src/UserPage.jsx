import React, { useEffect, useState } from "react";
import axios from "axios";
import { useParams } from "react-router-dom";


export default function UserPage() {
  const { userid } = useParams();
  const baseURL = "http://localhost:8080/";
  const [movieRatings, setMovieRatings] = useState([]);


   const fetchMovieRatings = async () => {
     try {
       const response = await axios.get(
         baseURL + `user/users/${userid}/movies/ratings`
       );
       const movieRatings = await Promise.all(
         response.data.map(async (rating_obj) => {
           console.log(rating_obj);
           const movieResponse = await axios.get(
             baseURL + `movies/${rating_obj.movieId}`
           );
           return {
             userRating: rating_obj.rating,
             ...movieResponse.data,
           };
         })
       );
       console.log(movieRatings);
        setMovieRatings(movieRatings);
     } catch (error) {
       console.error("There was an unknown error!", error);
       console.log("hereeeeeeeeeeeeeeeeeee");
     }
   };


  useEffect(() => {
      fetchMovieRatings();
  }, []);


   return (
     <div className="user-ratings-container">
       <h1> Account</h1>
       <div className="movie-ratings">
         <h2>Movie Ratings</h2>
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
