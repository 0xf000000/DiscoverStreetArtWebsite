package discover.streetart.main.repositery;

import discover.streetart.main.domain.StreetArt;
import org.springframework.data.repository.CrudRepository;

public interface StreetArtRepositery extends CrudRepository<StreetArt, Long> {
}
