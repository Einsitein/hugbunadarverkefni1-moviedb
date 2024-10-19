package is.hi.moviedb.service.implementations;

import is.hi.moviedb.model.User;
import is.hi.moviedb.security.JwtUtil;
import is.hi.moviedb.repository.UserRepository;
import is.hi.moviedb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.jwtUtil = new JwtUtil();
    }

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

    @Override
    public User getUser(String accessToken){
        String email = jwtUtil.verifyToken(accessToken);
        if (email == null) {
            return null;
        }
        User user = userRepository.findByEmail(email);
        return user;
    }

    @Override
    public List<User> getAllUsers(){
        List<User> users = userRepository.findAll();
        return users;
    }
}
