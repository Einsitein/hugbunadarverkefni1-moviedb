import React, { useEffect, useState } from "react";
import axios from "axios";

export default function MyAccount() {
  const token = localStorage.getItem("token");
  const [myEmail, setMyEmail] = useState("");
  const [myMovieRatings, setMyMovieRatings] = useState([
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
  const [newPassword, setNewPassword] = useState("");
  const [newPasswordAgain, setNewPasswordAgain] = useState("");
  const baseURL = "http://localhost:8080/";
  const [passwordSameMessage, setPasswordSameMessage] = useState("");

  useEffect(() => {
    const fetchMyEmail = async () => {
      try {
        const response = await axios.get(baseURL + "me", {
          headers: {
            token: token,
          },
        });
        setMyEmail(response.data.email);
      } catch (error) {
        console.error("There was an unknown error!", error);
      }
    };
    const fetchMyMovieRatings = async () => {
      try {
        const response = await axios.get(baseURL + "movies/ratings", {
          headers: {
            token: token,
          },
        });
        setMyMovieRatings(response.data);
      } catch (error) {
        console.error("There was an unknown error!", error);
      }
    };
    fetchMyEmail();
    fetchMyMovieRatings();
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
          await axios.delete("me", {
            headers: {
              accessToken: token,
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
              "Are you sure you want to delete your account? This action cannot be undone."
            )
          ) {
            try {
              await axios.delete("movies/ratings", {
                headers: {
                  accessToken: token,
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

   return (
     <div className="auth-container">
       <h1>My Account</h1>
       <div>
         <label>Email:</label>
         <div>{myEmail}</div>
       </div>
       <div className="movie-ratings">
         <h2>My Movie Ratings</h2>
         {myMovieRatings.map((movie, index) => (
           <div key={index} className="movie-rating">
             <img src={movie.image} alt={movie.title} />
             <div>
               <strong>{movie.title}</strong>
             </div>
             <div>Rating: {movie.rating}</div>
           </div>
         ))}
         <button
           onClick={handleDeleteRatings}
           className="delete-my-ratings-button"
         >
           Delete My Ratings
         </button>
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
