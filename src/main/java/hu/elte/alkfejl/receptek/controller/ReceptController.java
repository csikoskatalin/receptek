package hu.elte.alkfejl.receptek.controller;

import hu.elte.alkfejl.receptek.model.Recept;
import hu.elte.alkfejl.receptek.service.ReceptService;
import hu.elte.alkfejl.receptek.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static hu.elte.alkfejl.receptek.model.User.Role.*;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

@Controller
@RequestMapping("/recept")
public class ReceptController {

    @Autowired
    private ReceptService receptService;

    @Autowired
    private UserService userService;

    @RequestMapping("/home")
    public String listRecept(Model model) {
        if(this.userService.getUser().getRole()==ADMIN){
            model.addAttribute("receptek", receptService.receptek());
            model.addAttribute("recept", new Recept());
        }
        else if(this.userService.getUser().getRole()==USER){
            List recepteklist = receptService.publicReceptek();
            recepteklist.addAll(receptService.usersReceptek());
            Set receptek = new HashSet(recepteklist);
            model.addAttribute("receptek", receptek);
            model.addAttribute("recept", new Recept());
        }
        else if(this.userService.getUser().getRole()==GUEST){
            model.addAttribute("receptek", receptService.publicReceptek());
            model.addAttribute("recept", new Recept());
        }
        else{
            model.addAttribute("receptek", receptService.pendingReceptek());
            model.addAttribute("recept", new Recept());
        }


        return "home";
    }


    @PostMapping("/create")
    public String create(@ModelAttribute Recept recept) {
        recept.setStatus(Recept.Status.PRIVATE);
        recept.setUser(userService.getUser());
        receptService.create(recept);
        return "redirect:/recept/home";
    }


}