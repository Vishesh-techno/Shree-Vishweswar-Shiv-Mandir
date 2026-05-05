package in.vishweswar.controller;

import in.vishweswar.model.User;
import in.vishweswar.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public User signup(@RequestBody User user){
        return userService.signUp(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) throws Exception {
        return userService.login(user.getEmail(), user.getPassword());
    }

}

