package hu.elte.alkfejl.receptek.controller;
import hu.elte.alkfejl.receptek.model.User;
import hu.elte.alkfejl.receptek.service.UserService;
import hu.elte.alkfejl.receptek.service.exceptions.UserNotValidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import static hu.elte.alkfejl.receptek.model.User.Role.USER;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute(new User());
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User user, Model model) throws UserNotValidException {
        if (userService.isValid(user)) {
            userService.login(user);
            return "redirect:/recept/home";
        }
        model.addAttribute("loginFailed", true);
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user, Model model) {
        user.setRole(USER);
        userService.register(user);
        model.addAttribute("user", new User());
        return "login";
    }

}