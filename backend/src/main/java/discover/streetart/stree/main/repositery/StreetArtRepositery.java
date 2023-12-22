package discover.streetart.stree.main.repositery;

import discover.streetart.stree.main.domain.StreetArt;
import org.springframework.data.repository.CrudRepository;

public interface StreetArtRepositery extends CrudRepository<StreetArt, Long> {
}
