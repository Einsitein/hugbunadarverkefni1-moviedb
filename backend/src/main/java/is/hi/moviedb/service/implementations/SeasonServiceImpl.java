package is.hi.moviedb.service.implementations;

import is.hi.moviedb.model.Season;
import is.hi.moviedb.repository.SeasonRepository;
import is.hi.moviedb.service.SeasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeasonServiceImpl implements SeasonService {

    private final SeasonRepository seasonRepository;

    @Autowired
    public SeasonServiceImpl(SeasonRepository seasonRepository) {
        this.seasonRepository = seasonRepository;
    }

    @Override
    public List<Season> getSeasonsByTvShowId(int tvShowId) {
        return seasonRepository.findByTvShowId(tvShowId);
    }

    @Override
    public Season getSeasonById(int id) {
        return seasonRepository.findById(id).orElse(null);
    }
}
