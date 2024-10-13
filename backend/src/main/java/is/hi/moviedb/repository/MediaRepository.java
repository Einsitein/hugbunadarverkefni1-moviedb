package is.hi.moviedb.repository;

import is.hi.moviedb.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaRepository {
    findById(long mediaId);
    findByTitle(String title);
    findAll();

}