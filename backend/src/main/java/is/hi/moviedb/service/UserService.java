package is.hi.moviedb.service;

import is.hi.moviedb.model.User;
import is.hi.moviedb.model.Review;
import java.util.List;

public interface UserService {
    String register(String email, String password);

    String login(String email, String password);

    String changePassword(String accessToken, String newPassword);
    
    String deleteUser(String accessToken);

    User getMe(String accessToken);
    
    List<User> getAllUsers();

    Long getUserId(String email);
    List<Review> findTvShowsByUserId(long id);
}
