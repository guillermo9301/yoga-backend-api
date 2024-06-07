package pe.oly.yoga_backend_api.Auth.Public;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import pe.oly.yoga_backend_api.Jwt.JwtService;
import pe.oly.yoga_backend_api.User.Rol;
import pe.oly.yoga_backend_api.User.UserRepository;
import pe.oly.yoga_backend_api.User.Usuario;

@Service
@RequiredArgsConstructor
public class AuthService {

        private final UserRepository userRepository;
        private final JwtService jwtService;
        private final PasswordEncoder passwordEncoder;
        private final AuthenticationManager authenticationManager;

        public AuthResponse login(LoginRequest request) {
                authenticationManager
                                .authenticate(new UsernamePasswordAuthenticationToken(request.getCorreo(),
                                                request.getPassword()));
                UserDetails userDetails = userRepository.findByCorreo(request.getCorreo()).orElseThrow();
                Usuario usuario = (Usuario) userDetails;
                String token = jwtService.getToken(usuario);
                return AuthResponse.builder()
                                .token(token)
                                .nombre(usuario.getNombre())
                                .apellido_paterno(usuario.getApellido_paterno())
                                .correo(usuario.getCorreo())
                                .rol(usuario.getRol().name())
                                .build();
        }

        public AuthResponse register(RegisterRequest request) {
                Usuario usuario = Usuario.builder()
                                .correo(request.getCorreo())
                                .password(passwordEncoder.encode(request.getPassword()))
                                .nombre(request.getNombre())
                                .apellido_paterno(request.getApellido_paterno())
                                .apellido_materno(request.getApellido_materno())
                                .fec_nacimiento(request.getFec_nacimiento())
                                .id_tipo_documento(request.getId_tipo_documento())
                                .nro_documento(request.getNro_documento())
                                .celular(request.getCelular())
                                .fecha_registro(request.getFecha_registro())
                                .rol(Rol.ALUMNO)
                                .build();

                userRepository.save(usuario);

                return AuthResponse.builder()
                                .token(jwtService.getToken(usuario))
                                .nombre(request.getNombre())
                                .apellido_paterno(request.getApellido_paterno())
                                .correo(request.getCorreo())
                                .rol(usuario.getRol().name())
                                .build();

        }

}
