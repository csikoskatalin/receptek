package hu.elte.alkfejl.receptek.repository;

import hu.elte.alkfejl.receptek.model.User;
import org.omg.PortableInterceptor.USER_EXCEPTION;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.jws.soap.SOAPBinding;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>{

    Optional<User> findByUsername(String username);
    Optional<User> findByUsernameAndPassword(String username, String password);

}
