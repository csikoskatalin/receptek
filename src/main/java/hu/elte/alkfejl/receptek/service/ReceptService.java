package hu.elte.alkfejl.receptek.service;

import hu.elte.alkfejl.receptek.model.Recept;
import hu.elte.alkfejl.receptek.model.User;
import hu.elte.alkfejl.receptek.repository.ReceptRepository;
import hu.elte.alkfejl.receptek.repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Data
public class ReceptService {

    @Autowired
    private ReceptRepository receptRepository;

    @Autowired
    private UserService userService;


    public Iterable<Recept> receptek() {
        return receptRepository.findAll();
    }

    public List<Recept> publicReceptek() {
        return receptRepository.findByStatus(Recept.Status.PUBLIC);
    }

    public List<Recept> pendingReceptek() {
        return receptRepository.findByStatus(Recept.Status.PENDING);
    }

    public List<Recept> usersReceptek() {
        User user= userService.getUser();
        return receptRepository.findByUser(user);
    }

    public Recept read(int id) {
        return receptRepository.findOne(id);
    }

    public Recept create(Recept recept) {
        return receptRepository.save(recept);
    }

    public void delete(int id) {
        receptRepository.delete(id);
    }


}
