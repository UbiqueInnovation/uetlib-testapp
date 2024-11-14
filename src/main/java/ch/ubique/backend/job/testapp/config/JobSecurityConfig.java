package ch.ubique.backend.job.testapp.config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.beans.factory.annotation.Value;
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
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.spec.SecretKeySpec;

@Profile("!no-security")
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class JobSecurityConfig {

    private static final String SWAGGER = "SWAGGER";

    @Bean
    public SecurityFilterChain swaggerFilterChain(final HttpSecurity http) throws Exception {
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
    public SecurityFilterChain bearerFilterChain(final HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);

        final var statusEndpointPattern = "/v1/status/**";
        http.securityMatcher(statusEndpointPattern)
                .authorizeHttpRequests(
                        authorize ->
                                authorize
                                        .requestMatchers(HttpMethod.OPTIONS, statusEndpointPattern)
                                        .permitAll()
                                        .requestMatchers(statusEndpointPattern)
                                        .authenticated())
                .oauth2ResourceServer(customizer -> customizer.jwt(withDefaults()));

        http.authorizeHttpRequests(
                customizer ->
                        customizer
                                .requestMatchers("/actuator/prometheus", "/v1/**")
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
    public JwtDecoder jwtDecoder(
            @Value("${ch.ubique.backend.job.interface.secret}") final String secret) {
        return NimbusJwtDecoder.withSecretKey(new SecretKeySpec(secret.getBytes(), "HmacSHA256"))
                .macAlgorithm(MacAlgorithm.HS256)
                .build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
