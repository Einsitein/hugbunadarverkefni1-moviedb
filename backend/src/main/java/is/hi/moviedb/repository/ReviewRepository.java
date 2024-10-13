package is.hi.moviedb.repository;

import is.hi.moviedb.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository {
    getRatingByUser(long userId);
    getRatingByMovie(long mediaId)
}