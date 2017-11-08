package hu.elte.alkfejl.receptek.repository;


import com.sun.org.apache.regexp.internal.RE;
import hu.elte.alkfejl.receptek.model.Recept;
import hu.elte.alkfejl.receptek.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sun.misc.REException;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReceptRepository extends CrudRepository<Recept, Integer>{

    Optional<Recept> findByName(String name);
    Optional<Recept> findById(Integer id);

    List<Recept> findByStatus(Recept.Status status);
    List<Recept> findByUser(User user);


}
