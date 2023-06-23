package uz.pdp.store.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig{

    private final JwtFilter jwtFilter;
    private final JwtEntryPoint entryPoint;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.POST,"/user/registration-admin",
                        "/user/registration-customer").permitAll()
                .requestMatchers("/user/confirm").permitAll()
                .requestMatchers(HttpMethod.PUT, "/user/login").permitAll()
                .requestMatchers(HttpMethod.PUT, "/user/update").hasAnyAuthority("ROLE_ADMIN", "ROLE_CUSTOMER")
                .requestMatchers(HttpMethod.PUT,"/").hasAnyAuthority("ROLE_ADMIN")
                .requestMatchers(HttpMethod.DELETE,"/").hasAnyAuthority("ROLE_ADMIN")
                .requestMatchers(HttpMethod.POST,"/").hasAnyAuthority("ROLE_ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling().authenticationEntryPoint(entryPoint)
                .and()
                .build();
    }

}



