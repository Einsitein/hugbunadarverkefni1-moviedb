package is.hi.moviedb.repository;

import is.hi.moviedb.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repository interface for managing {@link Movie} entities.
 * Extends {@link JpaRepository} to provide CRUD operations and additional query methods.
 */
@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
    
    /**
     * Retrieves a list of movies where the name contains the specified search string, ignoring case.
     *
     * @param searchString the string to search for within movie names
     * @return a list of {@link Movie} objects that match the search criteria
     */
    List<Movie> findByNameContainingIgnoreCase(String searchString);
}
