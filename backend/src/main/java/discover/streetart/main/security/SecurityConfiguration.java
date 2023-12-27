package discover.streetart.main.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    // test user
    @Bean
   public InMemoryUserDetailsManager userDetailsManager(){

        UserDetails user = User.withDefaultPasswordEncoder()
                .username("foo")
                .password("foo")
                .roles("USER")
                .build();


        return new InMemoryUserDetailsManager(user);
   }

    // NOTE: delete this only for test purposes

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        http.csrf().disable().cors().disable()

                .authorizeHttpRequests().requestMatchers("/upload").authenticated()
                .requestMatchers("/**").permitAll()
                .and()
                .formLogin().and().authenticationProvider()

                .httpBasic();

        return http.build();

    }




}
