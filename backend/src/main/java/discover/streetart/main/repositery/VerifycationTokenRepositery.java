package discover.streetart.main.repositery;

import discover.streetart.main.domain.VerifycationToken;
import org.springframework.data.repository.CrudRepository;

public interface VerifycationTokenRepositery extends CrudRepository<VerifycationToken, Long> {



    public VerifycationToken findByToken(String token );
}
