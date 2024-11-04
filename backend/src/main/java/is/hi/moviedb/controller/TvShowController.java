package is.hi.moviedb.controller;

import is.hi.moviedb.model.TvShow;
import is.hi.moviedb.service.TvShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
}
