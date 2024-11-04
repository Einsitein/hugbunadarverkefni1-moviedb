package is.hi.moviedb.controller;

import is.hi.moviedb.model.TvShow;
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

    @Autowired
    public TvShowController(TvShowService tvShowService) {
        this.tvShowService = tvShowService;
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
}
