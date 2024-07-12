package pe.oly.yoga_backend_api.TrialClass;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import pe.oly.yoga_backend_api.Event.Event;
import pe.oly.yoga_backend_api.Event.EventRepository;
import pe.oly.yoga_backend_api.User.UserRepository;
import pe.oly.yoga_backend_api.User.Usuario;

@Service
@RequiredArgsConstructor
public class TrialClassService {
        private final TrialClassRepository trialClassRepository;
        private final EventRepository eventRepository;
        private final UserRepository userRepository;

        public TrialClass createTrialClass(TrialClass request) {

                Usuario alumno = userRepository.findById(request.getAlumnoId().getId()).orElseThrow(
                                () -> new IllegalArgumentException("Usuario no encontrado!"));
                Event evento = eventRepository.findById((request.getEventoId().getId())).orElseThrow(
                                () -> new IllegalArgumentException("Evento no encontrado!"));
                TrialClass trialClass = TrialClass.builder()
                                .alumnoId(alumno)
                                .eventoId(evento)
                                .build();

                return trialClassRepository.save(trialClass);
        }

        public UpdateTrialClassResponse updateTrialClass(int trialClassId, Long newEventId) {

                TrialClass trialClass = trialClassRepository.findById(trialClassId).orElseThrow(
                                () -> new IllegalArgumentException("No se encontró la clawse de prueba!"));

                Event newEvent = eventRepository.findById(newEventId).orElseThrow(
                                () -> new IllegalArgumentException("Evento no encontrado!"));

                trialClass.setEventoId(newEvent);

                TrialClass updatedTrialClass = trialClassRepository.save(trialClass);

                return UpdateTrialClassResponse.builder()
                                .fecha(updatedTrialClass.getEventoId().getFecha())
                                .horaInicio(updatedTrialClass.getEventoId().getHoraInicio())
                                .horaFin(updatedTrialClass.getEventoId().getHoraFin())
                                .mensaje("Se actualizó la clase de prueba exitosamente")
                                .build();

        }

        public TrialClassDTO geTrialClassByAlumnoId(int alumnoId) {

                TrialClass existingTrialClass = trialClassRepository.findByAlumno_Id(alumnoId).orElseThrow(
                                () -> new IllegalArgumentException("El alumno no tiene una Clase de prueba reservada"));

                ;

                return TrialClassDTO.builder()
                                .fecha(existingTrialClass.getEventoId().getFecha())
                                .horaInicio(existingTrialClass.getEventoId().getHoraInicio())
                                .horaFin(existingTrialClass.getEventoId().getHoraFin())
                                .nombre(existingTrialClass.getAlumnoId().getNombre())
                                .apellidoPaterno(existingTrialClass.getAlumnoId().getApellido_paterno())
                                .apellidoMaterno(existingTrialClass.getAlumnoId().getApellido_materno())
                                .correo(existingTrialClass.getAlumnoId().getCorreo())
                                .celular(existingTrialClass.getAlumnoId().getCelular())
                                .build();
        }

        public List<TrialClassDTO> getAllTrialClasses() {
                List<TrialClass> trialClasses = trialClassRepository.findAll();
                return trialClasses.stream().map(trialClass -> {
                        Event event = trialClass.getEventoId();
                        Usuario usuario = trialClass.getAlumnoId();

                        return TrialClassDTO.builder()
                                        .fecha(event.getFecha())
                                        .horaInicio(event.getHoraInicio())
                                        .horaFin(event.getHoraFin())
                                        .nombre(usuario.getNombre())
                                        .apellidoPaterno(usuario.getApellido_paterno())
                                        .apellidoMaterno(usuario.getApellido_materno())
                                        .correo(usuario.getCorreo())
                                        .celular(usuario.getCelular())
                                        .build();
                }).collect(Collectors.toList());
        }

}
