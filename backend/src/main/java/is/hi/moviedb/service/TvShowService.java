package is.hi.moviedb.service;

import is.hi.moviedb.model.TvShow;
import java.util.List;

public interface TvShowService {
    List<TvShow> getAllTvShows();
    List<TvShow> searchTvShowsByName(String searchString);
    TvShow getTvShowById(int id);
}
