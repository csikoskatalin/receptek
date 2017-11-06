package hu.elte.alkfejl.receptek.api;

import hu.elte.alkfejl.receptek.model.User;
import hu.elte.alkfejl.receptek.service.UserService;
import hu.elte.alkfejl.receptek.service.annotations.Role;
import hu.elte.alkfejl.receptek.service.exceptions.UserNotValidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static hu.elte.alkfejl.receptek.model.User.Role.ADMIN;
import static hu.elte.alkfejl.receptek.model.User.Role.MODERATOR;
import static hu.elte.alkfejl.receptek.model.User.Role.USER;

@RestController
@RequestMapping("/v1/user")
public class UserApiController {

    private final UserService userService;


    @Autowired
    public UserApiController(UserService userService) {
        this.userService = userService;
    }

    @Role({USER, ADMIN, MODERATOR})
    @GetMapping
    public ResponseEntity<User> user() {
        if (userService.isLoggedIn()) {
            return ResponseEntity.ok(userService.getUser());
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user) {
        try {
            return ResponseEntity.ok(userService.login(user));
        } catch (UserNotValidException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        return ResponseEntity.ok(userService.register(user));
    }

    @Role(ADMIN)
    @DeleteMapping("/{id}")
    private ResponseEntity delete(@PathVariable int id) {
        userService.delete(id);
        return ResponseEntity.ok().build();
    }

}
