package is.hi.moviedb.service;

import is.hi.moviedb.model.Season;
import java.util.List;

public interface SeasonService {
    List<Season> getSeasonsByTvShowId(int tvShowId);
    Season getSeasonById(int id);
}
