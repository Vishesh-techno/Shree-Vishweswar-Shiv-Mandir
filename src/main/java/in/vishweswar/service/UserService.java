package in.vishweswar.service;

import in.vishweswar.config.JwtUtil;
import in.vishweswar.model.User;
import in.vishweswar.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public User signUp(User user) {
        return userRepository.save(user);
    }

    public String login(String email, String password) throws Exception {
        User user = userRepository.findByEmail(email);
        if(user != null && user.getPassword().equals(password)){
            return jwtUtil.generateToken(user.getEmail(), user.getRole());
        }else{
            throw new Exception("Invalid Credentials");
        }
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
