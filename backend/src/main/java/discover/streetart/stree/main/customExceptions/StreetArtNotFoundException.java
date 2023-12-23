package discover.streetart.stree.main.customExceptions;

public class StreetArtNotFoundException extends RuntimeException {

    public StreetArtNotFoundException(Long id){
        super("could not find StreetArt Point id: " + id);

    }
}
