package is.hi.moviedb.service.implementations;

import java.util.List;

import is.hi.moviedb.model.User;
import is.hi.moviedb.security.JwtUtil;
import is.hi.moviedb.repository.UserRepository;
import is.hi.moviedb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import is.hi.moviedb.service.ReviewService;
import is.hi.moviedb.repository.TvShowRepository;
import is.hi.moviedb.model.Review;
import java.util.ArrayList;
import is.hi.moviedb.model.TvShow;
import is.hi.moviedb.service.TvShowService;


/**
 * Service implementation for managing {@link User} entities.
 * Implements the {@link UserService} interface to provide business logic for movie operations.
 */
@Service
public class UserServiceImpl implements UserService {

  /**
   * Repository for performing CRUD operations on {@link User} entities.
   */
  private final UserRepository userRepository;
  private final TvShowService tvShowService;
  private final ReviewService reviewService;
  private final JwtUtil jwtUtil;

  @Autowired
  public UserServiceImpl(UserRepository userRepository,TvShowService tvShowService,ReviewService reviewService) {
    this.userRepository = userRepository;
    this.tvShowService = tvShowService;
    this.reviewService = reviewService;
    this.jwtUtil = new JwtUtil();
  }

  /**
   * Creates a new {@link User} entitiy 
   *
   * @param email the email of the user to be created
   * @param password password for the User
   *
   * @return a String indicating if User exists or has been registered successfully.
   */
  @Override
  public String register(String email, String password) {
    if (userRepository.findByEmail(email) != null) {
      return "User already registered!";
    }
    User newUser = new User();
    newUser.setEmail(email);
    newUser.setPassword(password);
    userRepository.save(newUser);
    return "User registered successfully!";
  }

  /**
   * logs in a user, and creates a access token.
   *
   * @param email the email of the user 
   * @param password password of the User
   *
   * @return a token indicating the user has been logged in
   */
  @Override
  public String login(String email, String password) {
    User user = userRepository.findByEmail(email);
    if (user == null || !user.getPassword().equals(password)) {
      return "Invalid email or password!";
    }
    String token = jwtUtil.generateToken(email);
    return token;
  }

  @Override
  public String changePassword(String accessToken, String newPassword) {
    String email = jwtUtil.verifyToken(accessToken);
    if (email == null) {
      return "Invalid access token!";
    }
    User user = userRepository.findByEmail(email);
    user.setPassword(newPassword);
    userRepository.save(user);
    return "Password changed successfully!";
  }


  /**
   * Deletes a user by it's email
   *
   * @param accessToken a token containing the email of the user currently logged in.
   * @return String message.
   */
  @Override
  public String deleteUser(String accessToken) {
    String email = jwtUtil.verifyToken(accessToken);
    if (email == null) {
      return "Invalid access token!";
    }
    User user = userRepository.findByEmail(email);
    userRepository.delete(user);
    return "User deleted successfully!";
  }

  /**
   * retrieves a {@link User} by it's email
   *
   * @param accessToken a token containing the email of the user currently logged in.
   * @return {@link User} entitiy
   */
  @Override
  public User getMe(String accessToken){
    String email = jwtUtil.verifyToken(accessToken);
    if (email == null) {
      return null;
    }
    User user = userRepository.findByEmail(email);
    return user;
  }

  /**
   * retrieves all users
   * 
   * @return a List of {@link User} objects
   */
  @Override
  public List<User> getAllUsers(){
    List<User> users = userRepository.findAll();
    return users;
  } 

  /**
   * retrieves UserId by email
   *
   * @param email of the user
   * @return User id of the user.
   */
  @Override
  public Long getUserId(String email){
    User user = userRepository.findByEmail(email);
    if(user == null){
      return null;
    }
    return user.getId();
  }

  @Override
  public List<Review> findTvShowsByUserId(long id) {
    List<Review> reviews = reviewService.findByUserId(id);
    List<Review> shows = new ArrayList<>();

    for (Review review : reviews) {
      int tvShowId = review.getMovieId().intValue();
      TvShow show = tvShowService.getTvShowById(tvShowId);
      if (show != null) {
        shows.add(review);
      }
    }
    return shows;
  }
}
