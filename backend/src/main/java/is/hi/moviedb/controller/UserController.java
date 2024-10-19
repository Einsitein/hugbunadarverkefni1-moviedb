package is.hi.moviedb.controller;

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

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
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
        @RequestBody GetMeRequest request
    ) {
        String result = userService.deleteUser(request.getAccessToken());
        if (result = "Invalid access token!") {
            return new ResponseEntity<>(result, HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/me")
    public ResponseEntity<User> getMe( @RequestBody GetMeRequest request){
        User result = userService.getUser(request.getAccessToken());
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
}
