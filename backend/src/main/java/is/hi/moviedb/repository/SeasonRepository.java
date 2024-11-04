package is.hi.moviedb.repository;

import is.hi.moviedb.model.Season;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for accessing and managing {@link Season} entities in the database.
 * Provides methods to perform CRUD operations and custom queries for seasons.
 */
@Repository
public interface SeasonRepository extends JpaRepository<Season, Integer> {

    /**
     * Retrieves a list of seasons by the TV show ID they belong to.
     *
     * @param tvShowId the unique identifier of the TV show
     * @return a list of {@link Season} objects for the specified TV show
     */
    List<Season> findByTvShowId(int tvShowId);
}
