package discover.streetart.main.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import javax.sql.DataSource;


@Configuration
public class SecurityConfiguration {

    DataSource dataSource;
    public SecurityConfiguration( DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(NoOpPasswordEncoder.getInstance())
                .usersByUsernameQuery("select username, password, active  AS enabled from usr where username=?")
                .authoritiesByUsernameQuery("select u.username, ur.roles AS authority from usr u inner join user_role ur on u.id = ur.user_id where u.username=?");
    }


    @Bean
    public InMemoryUserDetailsManager userDetailsService(){
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("foo")
                .password("foo")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user);

    }






}
