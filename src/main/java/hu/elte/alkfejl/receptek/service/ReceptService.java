package hu.elte.alkfejl.receptek.service;

import hu.elte.alkfejl.receptek.model.Recept;
import hu.elte.alkfejl.receptek.repository.ReceptRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Data
public class ReceptService {

    @Autowired
    private ReceptRepository receptRepository;

    public Iterable<Recept> receptek() {
        return receptRepository.findAll();
    }


    public Recept create(Recept recept) {
        return receptRepository.save(recept);
    }

    public void delete(int id) {
        receptRepository.delete(id);
    }


}
