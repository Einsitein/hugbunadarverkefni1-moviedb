package is.hi.moviedb.repository;

import is.hi.moviedb.model.TvShow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repository interface for accessing and managing {@link TvShow} entities in the database.
 * Provides methods to perform CRUD operations and custom queries for TV shows.
 */
@Repository
public interface TvShowRepository extends JpaRepository<TvShow, Integer> {

    /**
     * Retrieves a list of TV shows that contain the specified search string in their name,
     * ignoring case sensitivity.
     *
     * @param searchString the string to search for within TV show names
     * @return a list of {@link TvShow} objects that match the search criteria
     */
    List<TvShow> findByNameContainingIgnoreCase(String searchString);
}
