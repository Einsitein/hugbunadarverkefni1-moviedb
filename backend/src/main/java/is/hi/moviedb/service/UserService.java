package is.hi.moviedb.service;

public interface UserService {
    String register(String email, String password);

    String login(String email, String password);

    String changePassword(String accessToken, String newPassword);
    
    String deleteUser(String accessToken);

    String getUser(String accessToken);
}
