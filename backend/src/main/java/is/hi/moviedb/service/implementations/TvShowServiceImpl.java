package is.hi.moviedb.service.implementations;

import is.hi.moviedb.model.TvShow;
import is.hi.moviedb.repository.TvShowRepository;
import is.hi.moviedb.service.TvShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TvShowServiceImpl implements TvShowService {

    private final TvShowRepository tvShowRepository;

    @Autowired
    public TvShowServiceImpl(TvShowRepository tvShowRepository) {
        this.tvShowRepository = tvShowRepository;
    }

    @Override
    public List<TvShow> getAllTvShows() {
        return tvShowRepository.findAll();
    }
}
