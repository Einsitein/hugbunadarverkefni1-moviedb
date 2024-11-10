import React, { useState, useEffect } from "react";
import axios from "axios";
import { Link } from "react-router-dom";

export default function TVShowsPage() {
  const baseURL = "http://localhost:8080/";
  const [popularTVShows, setPopularTVShows] = useState([]);
  const [searchedTVShows, setSearchedTVShows] = useState([]);
  const [searchString, setSearchString] = useState("");

  useEffect(() => {
    const fetchPopularTVShows = async () => {
      try {
        const response = await axios.get(baseURL + "tvshows");
        setPopularTVShows(response.data);
      } catch (error) {
        console.error("There was an unknown error!", error);
      }
    };
    fetchPopularTVShows();
  }, []);

  useEffect(() => {
    const searchTVShows = async () => {
      try {
        const response = await axios.get(
          baseURL + `tvshows/search/${searchString}`
        );
        setSearchedTVShows(response.data);
      } catch (error) {
        console.error("There was an unknown error!", error);
      }
    };
    if (searchString !== "") {
      searchTVShows();
    }
  }, [searchString]);

  const displayingTVShows = searchString === "" ? popularTVShows : searchedTVShows;

  return (
    <div className="tvshows-page">
      <h1>TV Shows Page</h1>
      <input
        type="text"
        placeholder="Search for a TV show..."
        value={searchString}
        onChange={(e) => setSearchString(e.target.value)}
        className="search-input"
      />
      <div className="displaying-tvshows">
        {displayingTVShows.map((show, index) => (
          <Link to={`/tvshows/${show.id}`} key={index} className="tvshow-item">
            <img src={show.images} alt={show.name} />
            <p>{show.name}</p>
          </Link>
        ))}
      </div>
    </div>
  );
}
