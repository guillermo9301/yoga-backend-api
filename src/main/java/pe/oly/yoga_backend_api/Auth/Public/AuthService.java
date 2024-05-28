package pe.oly.yoga_backend_api.Auth.Public;

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

    public AuthResponse login(LoginRequest request) {
        return null;
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
                .build();

    }

}
