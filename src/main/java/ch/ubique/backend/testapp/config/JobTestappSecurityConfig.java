package ch.ubique.backend.testapp.config;

import static org.springframework.security.config.Customizer.withDefaults;

import jakarta.servlet.DispatcherType;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Profile("!no-security")
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class JobTestappSecurityConfig {

    private static final String SWAGGER = "SWAGGER";

    @Bean
    public SecurityFilterChain swaggerFilterChain(final HttpSecurity http) {
        // swagger-ui
        http.securityMatcher("/api-docs/**", "/api-docs*")
                .authorizeHttpRequests(
                        customizer ->
                                customizer
                                        .requestMatchers("/api-docs/**", "/api-docs*")
                                        .hasRole(SWAGGER))
                .httpBasic(withDefaults());

        return http.build();
    }

    @Bean
    public SecurityFilterChain bearerFilterChain(final HttpSecurity http) {
        http.csrf(AbstractHttpConfigurer::disable);

        http.securityMatcher("/v1/**")
                .authorizeHttpRequests(
                        authorize ->
                                authorize
                                        .requestMatchers(HttpMethod.OPTIONS, "/v1/**")
                                        .permitAll()
                                        .requestMatchers(HttpMethod.GET, "/v1/info")
                                        .permitAll()
                                        .requestMatchers("/v1/**")
                                        .authenticated());

        http.authorizeHttpRequests(
                customizer ->
                        customizer
                                .dispatcherTypeMatchers(DispatcherType.ERROR)
                                .authenticated()
                                .requestMatchers("/actuator/prometheus", "/check")
                                .permitAll()
                                .anyRequest() // deny all others
                                .denyAll());
        return http.build();
    }

    @Bean
    InMemoryUserDetailsManager userDetailsService() {
        final var user =
                User.builder()
                        .username("ubswagger")
                        .password("$2a$10$kkl4QFGZPM2i.TwQPuXhMewLtDBvF.FRohAtMp7dZ4wq8q1N.U7yy")
                        .roles(SWAGGER)
                        .build();
        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
