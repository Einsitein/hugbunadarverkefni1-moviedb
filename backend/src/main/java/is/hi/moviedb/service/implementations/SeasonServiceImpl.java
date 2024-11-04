package is.hi.moviedb.service.implementations;

import is.hi.moviedb.model.Season;
import is.hi.moviedb.repository.SeasonRepository;
import is.hi.moviedb.service.SeasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service implementation for managing {@link Season} entities.
 * Provides business logic for accessing and manipulating seasons in the database.
 */
@Service
public class SeasonServiceImpl implements SeasonService {

    private final SeasonRepository seasonRepository;

    /**
     * Constructs a {@code SeasonServiceImpl} with the specified {@link SeasonRepository}.
     *
     * @param seasonRepository the repository for accessing seasons
     */
    @Autowired
    public SeasonServiceImpl(SeasonRepository seasonRepository) {
        this.seasonRepository = seasonRepository;
    }

    /**
     * Retrieves a list of seasons by the specified TV show ID.
     *
     * @param tvShowId the ID of the TV show for which to retrieve seasons
     * @return a list of {@link Season} objects associated with the given TV show
     */
    @Override
    public List<Season> getSeasonsByTvShowId(int tvShowId) {
        return seasonRepository.findByTvShowId(tvShowId);
    }

    /**
     * Retrieves a season by its unique ID.
     *
     * @param id the unique identifier of the season to retrieve
     * @return the {@link Season} object if found, or {@code null} if not found
     */
    @Override
    public Season getSeasonById(int id) {
        return seasonRepository.findById(id).orElse(null);
    }
}
