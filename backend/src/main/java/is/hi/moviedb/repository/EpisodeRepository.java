package is.hi.moviedb.repository;

import is.hi.moviedb.model.Episode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for accessing and managing {@link Episode} entities in the database.
 * Provides methods to perform CRUD operations and custom queries for episodes.
 */
@Repository
public interface EpisodeRepository extends JpaRepository<Episode, Integer> {

    /**
     * Retrieves a list of episodes by the season ID they belong to.
     *
     * @param seasonId the unique identifier of the season
     * @return a list of {@link Episode} objects for the specified season
     */
    List<Episode> findBySeasonId(int seasonId);
}
