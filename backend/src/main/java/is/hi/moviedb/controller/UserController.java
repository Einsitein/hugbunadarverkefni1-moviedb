package is.hi.moviedb.controller;

import java.util.List;
import is.hi.moviedb.model.User;

import is.hi.moviedb.model.Review;
import is.hi.moviedb.service.ReviewService;
import is.hi.moviedb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class RegisterRequest {
    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

class LoginRequest {
    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

class ChangePasswordRequest {
    private String accessToken;
    private String newPassword;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}

class GetMeRequest {
    private String accessToken;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}

class EmailRequest {
    private String email;

    // Getters and setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

/**
 * Controller class for handling HTTP requests related to users.
 * Provides endpoints to,
 * - create account
 * - log into account
 * - change password
 * - get account that is currently logged in.
 * - etc.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final ReviewService reviewService;


    @Autowired
    public UserController(UserService userService, ReviewService reviewService) {
        this.userService = userService;
        this.reviewService = reviewService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        String result = userService.register(request.getEmail(), request.getPassword());
        if (result.equals("User already registered!")) {
            return new ResponseEntity<>(result, HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        String result = userService.login(
            request.getEmail(),
            request.getPassword()
        );
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PatchMapping("/password")
    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordRequest request) {
        String result = userService.changePassword(
            request.getAccessToken(),
            request.getNewPassword()
        );
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
   * Deltes currently logged in user
   *
   * @return a response endity that indicates if account was deleted.
   */
    @DeleteMapping("/me")
    public ResponseEntity<String> deleteMe(
        @RequestHeader("Authorization") String accessToken
    ) {
        if (accessToken.startsWith("Bearer ")) {
            accessToken = accessToken.substring(7);
        }
        String result = userService.deleteUser(accessToken);
        if (result == "Invalid access token!") {
            return new ResponseEntity<>(result, HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    /**
   * Retrieves a responseEntity with all information about User
   *
   * @return a responseEntity<User> for the currently logged in user
   */
    @GetMapping("/me")
    public ResponseEntity<User> getMe(
        @RequestHeader("Authorization") String accessToken
    ) {
        if (accessToken.startsWith("Bearer ")) {
            accessToken = accessToken.substring(7);
        }
        System.out.println("Access Token: " + accessToken);
        User result = userService.getMe(accessToken);
        if (result == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    } 


    /**
   * Retrieves a list of all users.
   *
   * @return a list of ResponseEntity<User> objects.
   */
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> result = userService.getAllUsers();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
   * Retrieves a list of all Reviews a given user has created.
   *
   * @return a list of ResponseEntity<List<User>> objects.
   */
    @GetMapping("/users/{user_id}/movies/ratings")
    public ResponseEntity<List<Review>> getAllRatingsOfUser(
        @PathVariable String user_id
    ) {
        List<Review> result = reviewService.findByUserId(Long.parseLong(user_id));
        if(result == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/idbyemail")
    public ResponseEntity<Long> getEmailById(@RequestBody EmailRequest emailRequest) {
        Long result = userService.getUserId(emailRequest.getEmail());
        if (result == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @GetMapping("/me/movies/ratings/avg")
    public ResponseEntity<Double> getAverageRatingOfMe(
        @RequestHeader("Authorization") String accessToken
    ){
        User me = getMe(accessToken).getBody();

        List<Review> reviews = reviewService.findByUserId(me.getId());
        if (reviews == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        double result = reviewService.calculateAverageRating(reviews); 
        if (result < 0.0) { 
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } 

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
   * Retrieves the average rating of given User
   *
   * @return
   */
    @GetMapping("/users/{user_id}/movies/ratings/avg")
    public ResponseEntity<Double> getAverageRatingOfUser(
        @PathVariable String user_id
    ){
        List<Review> reviews = reviewService.findByUserId(Long.parseLong(user_id));
        if (reviews == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        double result = reviewService.calculateAverageRating(reviews); 
        if (result < 0.0) { 
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } 

        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    /**
     * Retrieves all tvshows the current user has rated.
     * @param accessToken
     * @return
     */
    @GetMapping("/me/ratings/tvshows/{id}")
    public ResponseEntity<List<Review>> getTvShowRatingOfMe(
        @RequestHeader("Authorization") String accessToken
    ) {
        User me = getMe(accessToken).getBody();
        List<Review> reviews = userService.findTvShowsByUserId(me.getId());
        if (!reviews.isEmpty()) { // Check if found
            return ResponseEntity.ok(reviews); // Return the rating with 200 OK status
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 Not found
        }
    }
}
