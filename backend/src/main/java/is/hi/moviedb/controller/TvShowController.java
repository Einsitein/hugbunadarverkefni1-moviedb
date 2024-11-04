package is.hi.moviedb.controller;

import is.hi.moviedb.model.Season;
import is.hi.moviedb.model.TvShow;
import is.hi.moviedb.service.SeasonService;
import is.hi.moviedb.service.TvShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tvshows")
public class TvShowController {

    private final TvShowService tvShowService;
    private final SeasonService seasonService;

    @Autowired
    public TvShowController(TvShowService tvShowService, SeasonService seasonService) {
        this.tvShowService = tvShowService;
        this.seasonService = seasonService;
    }

    @GetMapping
    public List<TvShow> getAllTvShows() {
        return tvShowService.getAllTvShows();
    }

    @GetMapping("/search/{searchstring}")
    public List<TvShow> searchTvShowsByName(@PathVariable String searchstring) {
        return tvShowService.searchTvShowsByName(searchstring);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TvShow> getTvShowById(@PathVariable int id) {
        TvShow tvShow = tvShowService.getTvShowById(id);
        return tvShow != null ? ResponseEntity.ok(tvShow) : ResponseEntity.notFound().build();
    }

    @GetMapping("/{tvShowId}/seasons")
    public ResponseEntity<List<Season>> getSeasonsByTvShowId(@PathVariable int tvShowId) {
        List<Season> seasons = seasonService.getSeasonsByTvShowId(tvShowId);
        return !seasons.isEmpty() ? ResponseEntity.ok(seasons) : ResponseEntity.notFound().build();
    }

    @GetMapping("/{tvShowId}/season/{seasonId}")
    public ResponseEntity<Season> getSeasonById(@PathVariable int tvShowId, @PathVariable int seasonId) {
        Season season = seasonService.getSeasonById(seasonId);
        return (season != null && season.getTvShow().getId() == tvShowId)
                ? ResponseEntity.ok(season)
                : ResponseEntity.notFound().build();
    }
}
