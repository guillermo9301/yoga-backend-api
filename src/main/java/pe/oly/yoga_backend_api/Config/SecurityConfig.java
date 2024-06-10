package pe.oly.yoga_backend_api.Config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import lombok.RequiredArgsConstructor;
import pe.oly.yoga_backend_api.Jwt.JwtAuthenticationFilter;

//Por defecto: SpringSecurity pone guards en todos los endpoints; 
//lo que se hace aqui es configurar cuales de esos endpoints 
//serán publicos y cuales serán privados

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

        private final JwtAuthenticationFilter jwtAuthenticationFilter;
        private final AuthenticationProvider authProvider;

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                return http
                                .csrf(csrf -> csrf.disable())
                                .authorizeHttpRequests(authRequest -> authRequest
                                                .requestMatchers(HttpMethod.GET).permitAll()
                                                .requestMatchers(HttpMethod.OPTIONS).permitAll()
                                                .requestMatchers("api/auth/**").permitAll()
                                                .anyRequest().authenticated())
                                .sessionManagement(
                                                sessionManager -> sessionManager
                                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                .authenticationProvider(authProvider)
                                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                                .build();
        }

        /*
         * @Bean
         * public CorsConfigurationSource corsConfigurationSource() {
         * CorsConfiguration configuration = new CorsConfiguration();
         * configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200")); //
         * Cambia esto a tu dominio de
         * // frontend si es necesario
         * configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE",
         * "OPTIONS"));
         * configuration.setAllowedHeaders(Arrays.asList("authorization",
         * "content-type", "x-auth-token"));
         * configuration.setExposedHeaders(Arrays.asList("x-auth-token"));
         * UrlBasedCorsConfigurationSource source = new
         * UrlBasedCorsConfigurationSource();
         * source.registerCorsConfiguration("/**", configuration);
         * return source;
         * }
         */

}
