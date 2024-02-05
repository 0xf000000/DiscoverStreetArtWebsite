package discover.streetart.main.repositery;

import discover.streetart.main.domain.PasswordResetToken;
import discover.streetart.main.domain.VerifycationToken;
import org.springframework.data.repository.CrudRepository;

public interface PasswordResetRepositery extends CrudRepository<PasswordResetToken, Long> {

    public PasswordResetToken findByToken(String token );
}
