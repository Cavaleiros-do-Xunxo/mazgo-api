package net.notfab.mazgo.spring;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;

import javax.ws.rs.HttpMethod;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                // Exceptions
                .and()
                .exceptionHandling()
                // Filters
                .and()
                // CORS
                .cors()
                .configurationSource((x) -> {
                    CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();
                    corsConfiguration.addAllowedMethod(HttpMethod.PUT);
                    corsConfiguration.addAllowedMethod(HttpMethod.PATCH);
                    corsConfiguration.addAllowedMethod(HttpMethod.OPTIONS);
                    corsConfiguration.addAllowedMethod(HttpMethod.DELETE);
                    return corsConfiguration;
                })
                // CSRF
                .and()
                .csrf().disable()
                // Other stuff
                .formLogin().disable()
                .logout().disable()
                .httpBasic().disable()
                .formLogin().disable();
    }

}
