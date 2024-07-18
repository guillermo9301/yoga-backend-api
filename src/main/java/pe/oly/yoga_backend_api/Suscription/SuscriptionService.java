package pe.oly.yoga_backend_api.Suscription;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import pe.oly.yoga_backend_api.Paquete.Paquete;
import pe.oly.yoga_backend_api.Paquete.PaqueteRepository;
import pe.oly.yoga_backend_api.User.UserRepository;
import pe.oly.yoga_backend_api.User.Usuario;

@Service
@RequiredArgsConstructor
public class SuscriptionService {

    private final SuscriptionRepository suscriptionRepository;
    private final PaqueteRepository paqueteRepository;
    private final UserRepository userRepository;

    public SuscriptionDTO create(Suscription request) {
        Paquete paquete = paqueteRepository.findById(request.getPaquete().getId()).orElseThrow(
                () -> new IllegalArgumentException("Paquete no encontrado!"));
        Usuario alumno = userRepository.findById(request.getAlumno().getId()).orElseThrow(
                () -> new IllegalArgumentException("Alumno no encontrado!"));

        Suscription nuevaSuscripcion = Suscription.builder()
                .alumno(alumno)
                .paquete(paquete)
                .estado(EstadoSuscripcion.ACTIVA).build();

        suscriptionRepository.save(nuevaSuscripcion);

        // construye la respuesta
        SuscriptionDTO response = new SuscriptionDTO(
                nuevaSuscripcion.getId(),
                nuevaSuscripcion.getFechaInicio(),
                nuevaSuscripcion.getFechaFin(),
                nuevaSuscripcion.getPaquete(),
                nuevaSuscripcion.getEstado());

        // Asocia la suscripción con el alumno
        alumno.setSuscripcionId(nuevaSuscripcion.getId());
        alumno.setSuscripcion(getAlumnoSuscription(alumno.getId()));
        userRepository.save(alumno);

        return response;
    }

    public void renewSuscription(Long id, updateSuscriptionRequest request) {
        suscriptionRepository.renewSuscription(id, request.getPaqueteId(), request.getFechaInicio(),
                request.getFechaFin(),
                request.getEstado());
    }

    public SuscriptionDTO getAlumnoSuscription(int alumnoId) {
        Usuario alumno = userRepository.findById(alumnoId).orElseThrow(
                () -> new IllegalArgumentException("El alumno no existe!"));

        Suscription suscripcion = suscriptionRepository.findByAlumnoId(alumno.getId()).orElseThrow(
                () -> new IllegalArgumentException("El alumno no cuenta con una suscripcion"));

        SuscriptionDTO response = new SuscriptionDTO(
                suscripcion.getId(),
                suscripcion.getFechaInicio(),
                suscripcion.getFechaFin(),
                suscripcion.getPaquete(),
                suscripcion.getEstado());

        return response;
    }

}
