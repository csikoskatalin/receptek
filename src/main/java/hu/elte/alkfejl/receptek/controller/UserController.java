package hu.elte.alkfejl.receptek.controller;
import hu.elte.alkfejl.receptek.model.User;
import hu.elte.alkfejl.receptek.service.ReceptService;
import hu.elte.alkfejl.receptek.service.UserService;
import hu.elte.alkfejl.receptek.service.exceptions.UserNotValidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static hu.elte.alkfejl.receptek.model.User.Role.ADMIN;
import static hu.elte.alkfejl.receptek.model.User.Role.USER;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private ReceptService receptService;


    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute(new User());
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User user, Model model) throws UserNotValidException {
        if (userService.isValid(user)) {
            userService.login(user);
            if(this.userService.getUser().getRole()==ADMIN){
                return "redirect:/user/users";
            }
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

    @GetMapping("/users")
    public String listUsers(Model model) {
        if(this.userService.getUser().getRole()==ADMIN){
            model.addAttribute("users", userService.listUsers());
            model.addAttribute("user", new User());
        }

        return "users";
    }

    @PostMapping("/users")
    public String deleteUser(User user) {
        if(this.userService.getUser().getRole()==ADMIN){
            receptService.deleteUsersRecept(user.getId());
            userService.delete(user.getId());
            return "users";
        }

        return "users";
    }



}