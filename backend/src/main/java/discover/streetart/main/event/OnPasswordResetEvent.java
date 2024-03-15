package discover.streetart.main.event;

import discover.streetart.main.domain.User;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;


@Getter
@Setter
public class OnPasswordResetEvent extends ApplicationEvent {

    private User user;
    private String appUrl;

    public OnPasswordResetEvent(User user, String appUrl){
        super(user);
        this.appUrl = appUrl;
        this.user = user;

    }


}
