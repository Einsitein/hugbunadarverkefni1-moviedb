package is.hi.moviedb.service;

import is.hi.moviedb.model.User;
import is.hi.moviedb.security.JwtUtil;
import is.hi.moviedb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.jwtUtil = new JwtUtil();
    }

    public String register(String email, String password) {
        // Check if the user already exists
        if (userRepository.findByEmail(email) != null) {
            return "User already registered!";
        }

        // Create and save the user to the database
        User newUser = new User();
        newUser.setEmail(email);
        newUser.setPassword(password); // In real applications, never store passwords in plain text!
        userRepository.save(newUser);

        return "User registered successfully!";
    }

    public String login(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user == null || !user.getPassword().equals(password)) {
            return "Invalid email or password!";
        }

        // Generate JWT token
        String token = jwtUtil.generateToken(email);

        return token;
    }

    public String change_password(String access_token, String new_password) {
        // Verify the JWT token
        String email = jwtUtil.verifyToken(access_token);
        if (email == null) {
            return "Invalid access token!";
        }

        // Update the user's password
        User user = userRepository.findByEmail(email);
        user.setPassword(new_password); // In real applications, never store passwords in plain text!
        userRepository.save(user);

        return "Password changed successfully!";
    }
}