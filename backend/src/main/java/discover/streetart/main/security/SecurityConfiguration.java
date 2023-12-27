package discover.streetart.main.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public InMemoryUserDetailsManager UserDetailsService(){
       PasswordEncoder encoder = passwordEncoder();
        UserDetails user = User.withUsername("foo")
                .password(encoder.encode("foo"))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user);

    }


}
