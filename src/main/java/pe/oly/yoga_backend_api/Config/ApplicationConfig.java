package pe.oly.yoga_backend_api.Config;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.RequiredArgsConstructor;
import pe.oly.yoga_backend_api.User.Rol;
import pe.oly.yoga_backend_api.User.UserRepository;
import pe.oly.yoga_backend_api.User.Usuario;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private final UserRepository userRepository;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByCorreo(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado!!"));
    }

    @Bean
    public CommandLineRunner init() {
        return args -> {
            String adminCorreo = "admin@correo.com";
            if (!userRepository.findByCorreo(adminCorreo).isPresent()) {
                Usuario defaultAdmin = new Usuario();
                defaultAdmin.setCorreo(adminCorreo);
                defaultAdmin.setNombre("admin");
                defaultAdmin.setApellido_paterno("adminAp");
                defaultAdmin.setApellido_materno("adminAm");
                defaultAdmin.setFec_nacimiento(LocalDate.of(2024, 6, 20));
                defaultAdmin.setId_tipo_documento(1);
                defaultAdmin.setNro_documento("74584231");
                defaultAdmin.setPassword(passwordEncoder().encode("admin"));
                defaultAdmin.setRol(Rol.ADMIN);
                userRepository.save(defaultAdmin);
                System.out.println("Se creo el usuario admin por default");
                System.out.println("credenciales:");
                System.out.println("correo: admin@correo.com");
                System.out.println("password: admin");
            } else {
                System.out.println("El usuario admin por defecto ya existe:");
                System.out.println("credenciales: ");
                System.out.println("correo: admin@correo.com");
                System.out.println("password: admin");
            }
            ;
        };
    }

}
