package hu.elte.alkfejl.receptek.controller;

import hu.elte.alkfejl.receptek.model.Recept;
import hu.elte.alkfejl.receptek.service.ReceptService;
import hu.elte.alkfejl.receptek.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;



@Controller
@RequestMapping("/recept")
public class ReceptController {

    @Autowired
    private ReceptService receptService;

    @Autowired
    private UserService userService;

    @RequestMapping("/home")
    public String listRecept(Model model) {
        model.addAttribute("receptek", receptService.receptek());
        model.addAttribute("recept", new Recept());

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