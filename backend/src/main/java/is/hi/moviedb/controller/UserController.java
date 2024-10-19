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
    String result = userService.login(request.getEmail(), request.getPassword());
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @PatchMapping("/password")
  public ResponseEntity<String> changePassword(@RequestBody ChangePasswordRequest request) {
    String result = userService.changePassword(request.getAccessToken(), request.getNewPassword());
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

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

  @GetMapping("/me")
  public ResponseEntity<User> getMe(@RequestHeader("Authorization") String accessToken) {
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

  @GetMapping("/users")
  public ResponseEntity<List<User>> getAllUsers() {
    List<User> result = userService.getAllUsers();
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

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

}