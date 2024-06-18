package pe.oly.yoga_backend_api.Schedule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import pe.oly.yoga_backend_api.Auth.Protected.BookTrialRequest;
import pe.oly.yoga_backend_api.Auth.Protected.BookTrialResponse;
import pe.oly.yoga_backend_api.User.UserRepository;
import pe.oly.yoga_backend_api.User.Usuario;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    public BookTrialResponse createOrUpdateSchedule(BookTrialRequest request) {
        // Se busca al alumno por su ID
        Usuario alumno = userRepository.findById(request.getId_alumno()).orElseThrow(
                () -> new IllegalArgumentException("Usuario no encontrado"));

        // Se busca si el Schedule ya existe
        Optional<Schedule> existingSchedule = scheduleRepository.findByFechaAndHoraInicioAndHoraFin(
                request.getFecha(), request.getHora_inicio(), request.getHora_fin());

        Schedule schedule;
        if (existingSchedule.isPresent()) {
            // Si ya existe eñ schedule, se agrega el alumno a la lista
            schedule = existingSchedule.get();

            if (!schedule.getAlumnos().contains(alumno)) {
                schedule.getAlumnos().add(alumno);
            } else {
                throw new IllegalArgumentException("El alumno ya esta registrado en esta clase");
            }
        } else {
            // si el schedule no existe, se crea uno nuevo
            schedule = Schedule.builder()
                    .fecha(request.getFecha())
                    .horaInicio(request.getHora_inicio())
                    .horaFin(request.getHora_fin())
                    .alumnos(new ArrayList<>(Collections.singletonList(alumno)))
                    .correo(request.getCorreo())
                    .nombre_alumno(request.getNombre_alumno())
                    .apellido_paterno_alumno(request.getApellido_paterno_alumno())
                    .apellido_materno_alumno(request.getApellido_materno_alumno())
                    .build();
        }

        Schedule savedSchedule = scheduleRepository.save(schedule);

        return BookTrialResponse.builder()
                .id(savedSchedule.getId())
                .fecha(request.getFecha())
                .hora_inicio(request.getHora_inicio())
                .hora_fin(request.getHora_fin())
                .id_alumno(alumno.getId())
                .mensaje("Reserva exitosa")
                .build();
    }

    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }
}
