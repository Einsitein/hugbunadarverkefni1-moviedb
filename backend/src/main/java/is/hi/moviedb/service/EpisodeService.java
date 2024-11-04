package is.hi.moviedb.service;

import is.hi.moviedb.model.Episode;
import java.util.List;

public interface EpisodeService {
    List<Episode> getEpisodesBySeasonId(int seasonId);
    Episode getEpisodeById(int id);
}
