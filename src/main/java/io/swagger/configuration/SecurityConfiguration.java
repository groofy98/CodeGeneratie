package io.swagger.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/account/**").hasAnyAuthority("ADMIN", "USER")
                .antMatchers("/account/{accountId}/details}").hasAnyAuthority("ADMIN")
                .antMatchers("/account/{accountId}/deactivate}").hasAnyAuthority("ADMIN")
                .antMatchers("/account/register").hasAnyAuthority("ADMIN")
                .antMatchers("/transactions/**").hasAnyAuthority("ADMIN", "USER")
                .antMatchers("/HomePage.html").hasAnyAuthority("ADMIN", "USER")
                .antMatchers("/transactions/**").hasAnyAuthority("ADMIN", "USER")
                .antMatchers("/Users.Overview/**").hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.GET,"/users/**").hasAnyAuthority("ADMIN", "USER")
                .and()
                .httpBasic()
                .and()
                .formLogin()
                .loginPage("/Login.html")
                .loginProcessingUrl("/perform_login")
                .defaultSuccessUrl("/HomePage.html", false)
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/perform_logout")
                .deleteCookies("JSESSIONID")
                .permitAll();

        ;

    }

    @Bean
    public PasswordEncoder encoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
