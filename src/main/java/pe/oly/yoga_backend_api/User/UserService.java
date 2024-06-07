package pe.oly.yoga_backend_api.User;

import java.util.List;

import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public UserResponse updateUser(UserRequest userRequest) {

        Usuario user = Usuario.builder()
                .id(userRequest.id)
                .nombre(userRequest.getNombre())
                .apellido_paterno(userRequest.getApellido_paterno())
                .apellido_materno(userRequest.getApellido_materno())
                .celular(userRequest.getCelular())
                .correo(userRequest.getCorreo())
                .fec_nacimiento(userRequest.getFec_nacimiento())
                .id_tipo_documento(userRequest.getId_tipo_documento())
                .nro_documento(userRequest.getNro_documento())
                .rol(userRequest.getRol())
                .build();

        userRepository.updateUser(user.id, user.nombre, user.apellido_paterno,
                user.apellido_materno, user.celular, user.correo, user.fec_nacimiento,
                user.id_tipo_documento, user.nro_documento, user.rol);

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
                    .rol(user.rol)
                    .build();
            return userDTO;
        }
        return null;
    }

    public List<Usuario> getAll() {
        return userRepository.findAll();
    }

}
