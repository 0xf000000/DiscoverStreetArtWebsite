package discover.streetart.main.repositery;

import discover.streetart.main.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 *
 */
public interface UserRepository extends CrudRepository<User, Long> {

    /**
     * returns a user from the selected email
     *
     * @param email
     * @return
     */
    public User findUserByEmail(String email);

    /**
     * returns a user by Username
     * @param username
     * @return
     */
    public User findUserByUsername(String username);




}
