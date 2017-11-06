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

    private Recept recept;

    public Iterable<Recept> receptek() {
        return receptRepository.findAll();
    }


    public Recept create(Recept recept) {

        this.recept = receptRepository.save(recept);
        return recept;
    }

    public void delete(int id) {

        receptRepository.delete(id);
    }


}
