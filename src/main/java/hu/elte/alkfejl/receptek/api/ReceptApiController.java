package hu.elte.alkfejl.receptek.api;


import hu.elte.alkfejl.receptek.model.Recept;
import hu.elte.alkfejl.receptek.service.ReceptService;
import hu.elte.alkfejl.receptek.service.UserService;
import hu.elte.alkfejl.receptek.service.annotations.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static hu.elte.alkfejl.receptek.model.User.Role.ADMIN;
import static hu.elte.alkfejl.receptek.model.User.Role.GUEST;
import static hu.elte.alkfejl.receptek.model.User.Role.USER;

@RestController
@RequestMapping("/v1/recept")

public class ReceptApiController {

    @Autowired
    private ReceptService receptService;

    @Autowired
    private UserService userService;

    @Role({USER, GUEST})
    @GetMapping
    private ResponseEntity<Iterable<Recept>> list() {
        Iterable<Recept> receptek = receptService.receptek();
        return ResponseEntity.ok(receptek);
    }

    @Role({ USER})
    @PostMapping
    private ResponseEntity<Recept> create(@RequestBody Recept recept) {
        Recept saved = receptService.create(recept);
        return ResponseEntity.ok(saved);
    }

    @Role(ADMIN)
    @DeleteMapping("/{id}")
    private ResponseEntity delete(@PathVariable int id) {
        receptService.delete(id);
        return ResponseEntity.ok().build();
    }



}
