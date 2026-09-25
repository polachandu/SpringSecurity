package org.learning.springsecurity.config;

import org.learning.springsecurity.exception.CustomAccessDeniedException;
import org.learning.springsecurity.exception.CustomBasicAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;

import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@Profile("!prod")
public class SecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) {
//        http.authorizeHttpRequests((requests) -> requests.anyRequest().permitAll());
//        http.authorizeHttpRequests((requests) -> requests.anyRequest().denyAll());
        http.sessionManagement(smc->smc.invalidSessionUrl("/invalidSession").maximumSessions(3).maxSessionsPreventsLogin(true))
                .redirectToHttps(httpSecurityHttpsRedirectConfigurer -> httpSecurityHttpsRedirectConfigurer.disable())
                .authorizeHttpRequests((requests) -> requests.requestMatchers("/myAccount","/myBalance","/myLoans","/myCards","/user").authenticated()
                .requestMatchers("/notices","/contact","/error","/register","/invalidSession").permitAll())
                .csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable());
        http.formLogin(withDefaults());
        http.httpBasic(httpSecurityHttpBasicConfigurer -> httpSecurityHttpBasicConfigurer.authenticationEntryPoint(new CustomBasicAuthenticationEntryPoint()));
        http.exceptionHandling(httpSecurityExceptionHandlingConfigurer -> httpSecurityExceptionHandlingConfigurer.accessDeniedHandler(new CustomAccessDeniedException()));
        return http.build();
    }


//    @Bean
//    public UserDetailsService userDetailsService(DataSource dataSource){
//        UserDetails user = User.withUsername("user").password("{noop}Userhasgreatpassword").authorities("read").build();
//        UserDetails admin = User.withUsername("admin").password("{bcrypt}$2a$12$fBZR74VLG35dzn7AAgDz9ugXfXD4/Pvz0j24BuVt87QJ52.VlADje").authorities("admin").build();
//        return new JdbcUserDetailsManager(dataSource);
//    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public CompromisedPasswordChecker compromisedPasswordChecker(){
        return new HaveIBeenPwnedRestApiPasswordChecker();
    }
}
