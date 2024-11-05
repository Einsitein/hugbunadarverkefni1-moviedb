package is.hi.moviedb.service.implementations;

import is.hi.moviedb.model.Episode;
import is.hi.moviedb.repository.EpisodeRepository;
import is.hi.moviedb.service.EpisodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service implementation for managing {@link Episode} entities.
 * Provides business logic for accessing and manipulating episodes in the database.
 */
@Service
public class EpisodeServiceImpl implements EpisodeService {

    private final EpisodeRepository episodeRepository;

    /**
     * Constructs an {@code EpisodeServiceImpl} with the specified {@link EpisodeRepository}.
     *
     * @param episodeRepository the repository for accessing episodes
     */
    @Autowired
    public EpisodeServiceImpl(EpisodeRepository episodeRepository) {
        this.episodeRepository = episodeRepository;
    }

    /**
     * Retrieves a list of episodes by the specified season ID.
     *
     * @param seasonId the ID of the season for which to retrieve episodes
     * @return a list of {@link Episode} objects associated with the given season
     */
    @Override
    public List<Episode> getEpisodesBySeasonId(int seasonId) {
        return episodeRepository.findBySeasonId(seasonId);
    }

    /**
     * Retrieves an episode by its unique ID.
     *
     * @param id the unique identifier of the episode to retrieve
     * @return the {@link Episode} object if found, or {@code null} if not found
     */
    @Override
    public Episode getEpisodeById(int id) {
        return episodeRepository.findById(id).orElse(null);
    }
}
