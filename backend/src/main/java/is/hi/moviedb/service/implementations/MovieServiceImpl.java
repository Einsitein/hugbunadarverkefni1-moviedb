package is.hi.moviedb.service.implementations;

import is.hi.moviedb.model.Movie;
import is.hi.moviedb.repository.MovieRepository;
import is.hi.moviedb.service.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service implementation for managing {@link Movie} entities.
 * Implements the {@link MediaService} interface to provide business logic for movie operations.
 */
@Service
public class MovieServiceImpl implements MediaService {

    /**
     * Repository for performing CRUD operations on {@link Movie} entities.
     */
    @Autowired
    private MovieRepository movieRepository;

    /**
     * Retrieves a list of all movies.
     *
     * @return a {@link List} of {@link Movie} objects
     */
    @Override
    public List<Movie> findAllMovies() {
        return movieRepository.findAll();
    }

    /**
     * Retrieves a movie by its unique ID.
     *
     * @param id the unique identifier of the movie
     * @return the {@link Movie} object if found, or {@code null} if not found
     */
    @Override
    public Movie findMovieById(int id) {
        return movieRepository.findById(id).orElse(null);
    }

    /**
     * Saves a new movie or updates an existing one.
     *
     * @param movie the {@link Movie} object to save or update
     * @return the saved {@link Movie} object
     */
    @Override
    public Movie saveMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    /**
     * Deletes a movie by its unique ID.
     *
     * @param id the unique identifier of the movie to delete
     */
    @Override
    public void deleteMovie(int id) {
        movieRepository.deleteById(id);
    }

    /**
     * Searches for movies that contain the given search string in their name, case-insensitive.
     *
     * @param searchString the string to search for within movie names
     * @return a {@link List} of {@link Movie} objects that match the search criteria
     */
    @Override
    public List<Movie> searchMovies(String searchString) {
        return movieRepository.findByNameContainingIgnoreCase(searchString);
    }
}
