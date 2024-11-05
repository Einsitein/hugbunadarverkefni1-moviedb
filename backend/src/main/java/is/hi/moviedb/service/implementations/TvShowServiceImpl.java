package is.hi.moviedb.service.implementations;

import is.hi.moviedb.model.TvShow;
import is.hi.moviedb.repository.TvShowRepository;
import is.hi.moviedb.service.TvShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service implementation for managing {@link TvShow} entities.
 * Provides business logic for accessing and manipulating TV shows in the database.
 */
@Service
public class TvShowServiceImpl implements TvShowService {

    private final TvShowRepository tvShowRepository;

    /**
     * Constructs a {@code TvShowServiceImpl} with the specified {@link TvShowRepository}.
     *
     * @param tvShowRepository the repository for accessing TV shows
     */
    @Autowired
    public TvShowServiceImpl(TvShowRepository tvShowRepository) {
        this.tvShowRepository = tvShowRepository;
    }

    /**
     * Retrieves all TV shows.
     *
     * @return a list of all {@link TvShow} objects in the database
     */
    @Override
    public List<TvShow> getAllTvShows() {
        return tvShowRepository.findAll();
    }

    /**
     * Searches for TV shows by name, ignoring case.
     *
     * @param searchString the name or part of the name to search for
     * @return a list of {@link TvShow} objects that match the search criteria
     */
    @Override
    public List<TvShow> searchTvShowsByName(String searchString) {
        return tvShowRepository.findByNameContainingIgnoreCase(searchString);
    }

    /**
     * Retrieves a TV show by its unique ID.
     *
     * @param id the unique identifier of the TV show
     * @return the {@link TvShow} object if found, or {@code null} if not found
     */
    @Override
    public TvShow getTvShowById(int id) {
        return tvShowRepository.findById(id).orElse(null);
    }
}
