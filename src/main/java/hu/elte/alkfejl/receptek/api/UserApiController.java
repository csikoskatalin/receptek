package hu.elte.alkfejl.receptek.api;

import hu.elte.alkfejl.receptek.model.Recept;
import hu.elte.alkfejl.receptek.model.User;
import hu.elte.alkfejl.receptek.service.ReceptService;
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
    private ReceptService receptService;

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
    @CrossOrigin
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user) {
        try {
            return ResponseEntity.ok(userService.login(user));
        } catch (UserNotValidException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @CrossOrigin
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        return ResponseEntity.ok(userService.register(user));
    }

    @CrossOrigin
    @Role(ADMIN)
    @DeleteMapping("users/{id}")
    private ResponseEntity<User> delete(@PathVariable int id) {
        for ( Recept r : this.receptService.usersReceptekById(id)) {
            this.receptService.delete(r.getId());
        }
        userService.delete(id);
        return ResponseEntity.ok().build();
    }

    @CrossOrigin
    @GetMapping("/logout")
    public ResponseEntity logout() {
        this.userService.setUser(null);
        return ResponseEntity.ok().build();
    }
    @CrossOrigin
    @Role({ADMIN})
    @GetMapping("/users")
    private ResponseEntity<Iterable<User>> list() {
        Iterable<User> users = userService.listUsers();
        return ResponseEntity.ok(users);
    }

}
