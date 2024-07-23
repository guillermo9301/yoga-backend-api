package pe.oly.yoga_backend_api.User;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.apache.catalina.startup.ClassLoaderFactory.Repository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserResponse updateUser(Integer id, UserRequest userRequest) throws Exception {

        // Verificar si el usuario existe
        Optional<Usuario> existingUserOpt = userRepository.findById(id);
        if (!existingUserOpt.isPresent()) {
            throw new UsernameNotFoundException("User not found with id " + id);
        }

        Usuario existingUser = existingUserOpt.get();

        // Actualizar los campos del usuario existente
        existingUser.setNombre(userRequest.getNombre());
        existingUser.setApellido_paterno(userRequest.getApellido_paterno());
        existingUser.setApellido_materno(userRequest.getApellido_materno());
        existingUser.setCelular(userRequest.getCelular());
        existingUser.setCorreo(userRequest.getCorreo());
        existingUser.setFec_nacimiento(userRequest.getFec_nacimiento());
        existingUser.setId_tipo_documento(userRequest.getId_tipo_documento());
        existingUser.setNro_documento(userRequest.getNro_documento());
        existingUser.setRol(userRequest.getRol());

        // Guardar el usuario actualizado
        userRepository.save(existingUser);

        return new UserResponse("Los datos se actualizaron satisfactoriamente");
    }

    public UserDTO getUser(Integer id) {
        Usuario user = userRepository.findById(id).orElse(null);

        if (user != null) {
            UserDTO userDTO = UserDTO.builder()
                    .id(user.id)
                    .nombre(user.nombre)
                    .apellido_paterno(user.apellido_paterno)
                    .apellido_materno(user.apellido_materno)
                    .correo(user.correo)
                    .fec_nacimiento(user.fec_nacimiento)
                    .id_tipo_documento(user.id_tipo_documento)
                    .nro_documento(user.nro_documento)
                    .celular(user.celular)
                    .fecha_registro(user.fecha_registro)
                    .fechaActualizacion((user.fechaActualizacion))
                    .suscripcionId(user.getSuscripcionId())
                    .inscripciones(user.getCantidadInscipciones())
                    .rol(user.rol)
                    .build();
            return userDTO;
        }
        return null;
    }

    public List<Usuario> getAll() {
        return userRepository.findAll();
    }

    public Usuario addUser(Usuario request) {
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
                                .rol(request.getRol())
                                .build();
                    userRepository.save(usuario);
                    return usuario;
    }

    public List<Rol> getRoles() {
        return Arrays.asList(Rol.values());
    }
}