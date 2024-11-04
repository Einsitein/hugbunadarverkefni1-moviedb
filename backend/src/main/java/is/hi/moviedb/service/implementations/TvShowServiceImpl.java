package is.hi.moviedb.service.implementations;

import is.hi.moviedb.model.TvShow;
import is.hi.moviedb.repository.TvShowRepository;
import is.hi.moviedb.service.TvShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

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

    @Override
    public List<TvShow> searchTvShowsByName(String searchString) {
        return tvShowRepository.findByNameContainingIgnoreCase(searchString);
    }

    @Override
    public TvShow getTvShowById(int id) {
      return tvShowRepository.findById(id).orElse(null);
  }
}
