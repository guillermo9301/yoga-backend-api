package pe.oly.yoga_backend_api.TrialClass;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import pe.oly.yoga_backend_api.Auth.Protected.BookTrialRequest;
import pe.oly.yoga_backend_api.Auth.Protected.BookTrialResponse;
import pe.oly.yoga_backend_api.User.UserRepository;
import pe.oly.yoga_backend_api.User.Usuario;

@Service
@RequiredArgsConstructor
public class TrialClassService {
    private final TrialClassRepository trialClassRepository;
    private final UserRepository userRepository;

    public BookTrialResponse book(BookTrialRequest request) {
        Usuario alumno = userRepository.findById(request.getId_alumno()).orElseThrow(
                () -> new IllegalArgumentException("Usuario no encontrado"));

        TrialClass trialClass = TrialClass.builder()
                .fecha(request.getFecha())
                .hora_inicio(request.getHora_inicio())
                .hora_fin(request.getHora_fin())
                .alumno(alumno)
                .correo(request.getCorreo())
                .nombre_alumno(request.getNombre_alumno())
                .apellido_paterno_alumno(request.getApellido_paterno_alumno())
                .apellido_materno_alumno(request.getApellido_materno_alumno())
                .build();

        TrialClass savedTrialClass = trialClassRepository.save(trialClass);

        return BookTrialResponse.builder()
                .id(savedTrialClass.getId())
                .fecha(request.getFecha())
                .hora_inicio(request.getHora_inicio())
                .hora_fin(request.getHora_fin())
                .id_alumno(alumno.getId())
                .mensaje("Reserva exitosa")
                .build();
    }

}
