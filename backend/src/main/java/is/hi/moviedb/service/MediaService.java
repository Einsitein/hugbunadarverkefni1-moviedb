package is.hi.moviedb.service;

import is.hi.moviedb.model.Movie;
import java.util.List;

/**
 * Service interface for managing {@link Movie} entities.
 * Defines the contract for movie-related operations such as retrieval, creation, updating, deletion, and searching.
 */
public interface MediaService {

    /**
     * Retrieves a list of all movies in the database.
     *
     * @return a {@link List} of {@link Movie} objects representing all movies
     */
    List<Movie> findAllMovies();

    /**
     * Retrieves a specific movie by its unique ID.
     *
     * @param id the unique identifier of the movie to retrieve
     * @return the {@link Movie} object if found, or {@code null} if no movie with the specified ID exists
     */
    Movie findMovieById(int id);

    /**
     * Saves a new movie to the database or updates an existing one.
     *
     * @param movie the {@link Movie} object to save or update
     * @return the saved {@link Movie} object with any updates applied (e.g., generated ID)
     */
    Movie saveMovie(Movie movie);

    /**
     * Deletes a movie from the database by its unique ID.
     *
     * @param id the unique identifier of the movie to delete
     */
    void deleteMovie(int id);

    /**
     * Searches for movies that contain the specified search string in their name, ignoring case.
     *
     * @param searchString the string to search for within movie names
     * @return a {@link List} of {@link Movie} objects that match the search criteria
     */
    List<Movie> searchMovies(String searchString);
}
