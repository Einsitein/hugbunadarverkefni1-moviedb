package is.hi.moviedb.repository;

import is.hi.moviedb.model.Season;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeasonRepository extends JpaRepository<Season, Integer> {
    List<Season> findByTvShowId(int tvShowId);
}
