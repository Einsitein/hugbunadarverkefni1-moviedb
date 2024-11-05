package is.hi.moviedb.controller;

import is.hi.moviedb.model.Episode;
import is.hi.moviedb.model.Season;
import is.hi.moviedb.model.TvShow;
import is.hi.moviedb.service.EpisodeService;
import is.hi.moviedb.service.SeasonService;
import is.hi.moviedb.service.TvShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller for handling requests related to TV shows, seasons, and episodes.
 */
@RestController
@RequestMapping("/tvshows")
public class TvShowController {

    private final TvShowService tvShowService;
    private final SeasonService seasonService;
    private final EpisodeService episodeService;

    @Autowired
    public TvShowController(TvShowService tvShowService, SeasonService seasonService, EpisodeService episodeService) {
        this.tvShowService = tvShowService;
        this.seasonService = seasonService;
        this.episodeService = episodeService;
    }

    /**
     * Retrieves a list of all TV shows.
     *
     * @return a list of all {@link TvShow} objects
     */
    @GetMapping
    public List<TvShow> getAllTvShows() {
        return tvShowService.getAllTvShows();
    }

    /**
     * Searches for TV shows by name.
     *
     * @param searchstring the search string used to find matching TV shows
     * @return a list of matching {@link TvShow} objects
     */
    @GetMapping("/search/{searchstring}")
    public List<TvShow> searchTvShowsByName(@PathVariable String searchstring) {
        return tvShowService.searchTvShowsByName(searchstring);
    }

    /**
     * Retrieves a single TV show by its ID.
     *
     * @param id the unique identifier of the TV show
     * @return a {@link ResponseEntity} containing the TV show if found, or 404 status if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<TvShow> getTvShowById(@PathVariable int id) {
        TvShow tvShow = tvShowService.getTvShowById(id);
        return tvShow != null ? ResponseEntity.ok(tvShow) : ResponseEntity.notFound().build();
    }

    /**
     * Retrieves all seasons of a specific TV show by its ID.
     *
     * @param tvShowId the unique identifier of the TV show
     * @return a {@link ResponseEntity} containing a list of {@link Season} objects, or 404 if not found
     */
    @GetMapping("/{tvShowId}/seasons")
    public ResponseEntity<List<Season>> getSeasonsByTvShowId(@PathVariable int tvShowId) {
        List<Season> seasons = seasonService.getSeasonsByTvShowId(tvShowId);
        return !seasons.isEmpty() ? ResponseEntity.ok(seasons) : ResponseEntity.notFound().build();
    }

    /**
     * Retrieves a specific season by its ID within a given TV show.
     *
     * @param tvShowId the unique identifier of the TV show
     * @param seasonId the unique identifier of the season
     * @return a {@link ResponseEntity} containing the {@link Season} if found, or 404 if not found
     */
    @GetMapping("/{tvShowId}/season/{seasonId}")
    public ResponseEntity<Season> getSeasonById(@PathVariable int tvShowId, @PathVariable int seasonId) {
        Season season = seasonService.getSeasonById(seasonId);
        return (season != null && season.getTvShow().getId() == tvShowId)
                ? ResponseEntity.ok(season)
                : ResponseEntity.notFound().build();
    }

    /**
     * Retrieves all episodes within a specific season of a TV show.
     *
     * @param tvShowId the unique identifier of the TV show
     * @param seasonId the unique identifier of the season
     * @return a {@link ResponseEntity} containing a list of {@link Episode} objects, or 404 if not found
     */
    @GetMapping("/{tvShowId}/season/{seasonId}/episodes")
    public ResponseEntity<List<Episode>> getEpisodesBySeasonId(@PathVariable int tvShowId, @PathVariable int seasonId) {
        Season season = seasonService.getSeasonById(seasonId);
        if (season != null && season.getTvShow().getId() == tvShowId) {
            List<Episode> episodes = episodeService.getEpisodesBySeasonId(seasonId);
            return ResponseEntity.ok(episodes);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Retrieves a specific episode by its ID within a given season of a TV show.
     *
     * @param tvShowId   the unique identifier of the TV show
     * @param seasonId   the unique identifier of the season
     * @param episodeId  the unique identifier of the episode
     * @return a {@link ResponseEntity} containing the {@link Episode} if found, or 404 if not found
     */
    @GetMapping("/{tvShowId}/season/{seasonId}/episode/{episodeId}")
    public ResponseEntity<Episode> getEpisodeById(@PathVariable int tvShowId, @PathVariable int seasonId, @PathVariable int episodeId) {
        Episode episode = episodeService.getEpisodeById(episodeId);
        if (episode != null && episode.getSeason().getId() == seasonId && episode.getSeason().getTvShow().getId() == tvShowId) {
            return ResponseEntity.ok(episode);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
