package is.hi.moviedb.controller;

import is.hi.moviedb.model.Movie;
import is.hi.moviedb.service.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controller class for handling HTTP requests related to movies.
 * Provides endpoints to retrieve all movies, get a movie by ID, and search for movies by name.
 */
@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MediaService mediaService;

    /**
     * Retrieves a list of all movies.
     *
     * @return a list of {@link Movie} objects
     */
    @GetMapping
    public List<Movie> getAllMovies() {
        return mediaService.findAllMovies();
    }

    /**
     * Retrieves a movie by its unique ID.
     *
     * @param id the unique identifier of the movie
     * @return a {@link ResponseEntity} containing the {@link Movie} if found, or a 404 Not Found status if not
     */
    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable int id) {
        Movie movie = mediaService.findMovieById(id);
        if (movie != null) {
            return ResponseEntity.ok(movie);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Searches for movies that contain the given search string in their name, case-insensitive.
     *
     * @param searchString the string to search for in movie names
     * @return a list of {@link Movie} objects that match the search criteria
     */
    @GetMapping("/search/{searchString}")
    public List<Movie> searchMovies(@PathVariable String searchString) {
        return mediaService.searchMovies(searchString);
    }
}
