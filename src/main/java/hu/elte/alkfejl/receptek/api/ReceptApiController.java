package hu.elte.alkfejl.receptek.api;


import hu.elte.alkfejl.receptek.model.Recept;
import hu.elte.alkfejl.receptek.service.ReceptService;
import hu.elte.alkfejl.receptek.service.UserService;
import hu.elte.alkfejl.receptek.service.annotations.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static hu.elte.alkfejl.receptek.model.User.Role.*;

@RestController
@RequestMapping("/v1/recept")

public class ReceptApiController {

    @Autowired
    private ReceptService receptService;

    @Autowired
    private UserService userService;
    @CrossOrigin
    @Role({ADMIN,USER,GUEST,MODERATOR})
    @GetMapping
    private ResponseEntity<Iterable<Recept>> list() {
        if(this.userService.getUser().getRole()==ADMIN){
            Iterable<Recept> receptek = receptService.receptek();
            return ResponseEntity.ok(receptek);
        }
        else if(this.userService.getUser().getRole()==USER){
            List recepteklist = receptService.publicReceptek();
            recepteklist.addAll(receptService.usersReceptek());
            Set receptekSet = new HashSet(recepteklist);
            Iterable<Recept> receptek = receptekSet;
            return ResponseEntity.ok(receptek);
        }
        else if(this.userService.getUser().getRole()==GUEST){
            Iterable<Recept> receptek = receptService.publicReceptek();
            return ResponseEntity.ok(receptek);
        }
        else{
            Iterable<Recept> receptek = receptService.pendingReceptek();
            return ResponseEntity.ok(receptek);
        }

    }

    @Role({ USER})
    @PostMapping
    private ResponseEntity<Recept> create(@RequestBody Recept recept) {
        Recept saved = receptService.create(recept);
        return ResponseEntity.ok(saved);
    }

    @Role({ADMIN, USER, GUEST,MODERATOR})
    @GetMapping("/{id}")
    private ResponseEntity<Recept> read(@PathVariable String id) {
        Recept read = receptService.read(Integer.parseInt(id));
        return ResponseEntity.ok(read);
    }

    @Role(ADMIN)
    @DeleteMapping("/{id}")
    private ResponseEntity delete(@PathVariable int id) {
        receptService.delete(id);
        return ResponseEntity.ok().build();
    }



}
