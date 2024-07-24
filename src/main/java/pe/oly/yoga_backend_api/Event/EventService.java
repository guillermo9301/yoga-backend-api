package pe.oly.yoga_backend_api.Event;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import pe.oly.yoga_backend_api.CustomValidations.EventValidationService;
import pe.oly.yoga_backend_api.Suscription.EstadoSuscripcion;
import pe.oly.yoga_backend_api.Suscription.Suscription;
import pe.oly.yoga_backend_api.Suscription.SuscriptionDTO;
import pe.oly.yoga_backend_api.Suscription.SuscriptionRepository;
import pe.oly.yoga_backend_api.Suscription.SuscriptionService;
import pe.oly.yoga_backend_api.User.UserRepository;
import pe.oly.yoga_backend_api.User.Usuario;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final SuscriptionRepository suscriptionRepository;
    private final EventValidationService eventValidationService;
    private final SuscriptionService suscriptionService;

    public CreateEventResponse createEvent(Event event) {

        eventValidationService.validateEventDates(event.getFecha());
        eventValidationService.validateEventTimes(event.getFecha(), event.getHoraInicio(), event.getHoraFin());

        if (event.isRecurrente()) {
            LocalDate fechaActual = event.getFecha();
            List<Event> eventosRecurrentes = new ArrayList<>();

            while (!fechaActual.isAfter(event.getFechaFinRecurrencia())) {
                Event eventoRecurrente = Event.builder()
                        .fecha(fechaActual)
                        .horaInicio(event.getHoraInicio())
                        .horaFin(event.getHoraFin())
                        .capacidad(event.getCapacidad())
                        .cuposDisponibles(event.getCapacidad())
                        .recurrente(event.isRecurrente())
                        .fechaFinRecurrencia(event.getFechaFinRecurrencia())
                        .alumnos(new ArrayList<>())
                        .build();

                eventosRecurrentes.add(eventoRecurrente);
                fechaActual = fechaActual.plusWeeks(1);
            }
            eventRepository.saveAll(eventosRecurrentes);

            new CreateEventResponse();
            CreateEventResponse response = CreateEventResponse.builder()
                    .mensaje("Se han creado " + eventosRecurrentes.size() + " eventos")
                    .fechaInicio(eventosRecurrentes.get(0).getFecha())
                    .fechaFinRecurrencia(eventosRecurrentes.get(eventosRecurrentes.size() - 1).getFecha())
                    .horaInicio(event.getHoraInicio())
                    .horaFin(event.getHoraFin())
                    .recurrente(event.isRecurrente())
                    .build();

            return response;
        } else {
            event.setCuposDisponibles(event.getCapacidad());
            Event savedEvent = eventRepository.save(event);

            CreateEventResponse response = CreateEventResponse.builder()
                    .mensaje("Evento creado exitosamente.")
                    .fechaInicio(savedEvent.getFecha())
                    .horaInicio(savedEvent.getHoraInicio())
                    .horaFin(savedEvent.getHoraFin())
                    .build();

            return response;
        }
    }

    public UpdateEventResponse updateEvent(Long id, Event event) {
        eventValidationService.validateEventDates(event.getFecha());
        eventValidationService.validateEventTimes(event.getFecha(), event.getHoraInicio(), event.getHoraFin());

        try {
            Optional<Event> existingEventOpt = eventRepository.findById(id);
            if (!existingEventOpt.isPresent()) {
                throw new IllegalArgumentException("No se encontró el evento " + id);
            }

            Event existingEvent = existingEventOpt.get();

            // Se actualizan los datos del evento
            existingEvent.setFecha(event.getFecha());
            existingEvent.setHoraInicio(event.getHoraInicio());
            existingEvent.setHoraFin(event.getHoraFin());
            existingEvent.setCapacidad(event.getCapacidad());
            existingEvent.setCuposDisponibles(event.getCuposDisponibles());

            // Se guarda el evento actualizado
            Event updatedEvent = eventRepository.save(existingEvent);
            return UpdateEventResponse.builder()
                    .mensaje("Se actualizó el evento satisfactoriamente")
                    .eventoiId(updatedEvent.getId())
                    .fecha(updatedEvent.getFecha())
                    .horaInicio(updatedEvent.getHoraInicio())
                    .horaFin(updatedEvent.getHoraFin())
                    .capacidad(updatedEvent.getCapacidad())
                    .cuposDisponibles(updatedEvent.getCuposDisponibles())
                    .build();
        } catch (IllegalArgumentException e) {
            return UpdateEventResponse.builder()
                    .mensaje("Error: " + e.getMessage())
                    .build();
        } catch (Exception e) {
            return UpdateEventResponse.builder()
                    .mensaje("Ocurrió un error inesperado al actualizar el evento")
                    .build();
        }

    }

    public List<Event> getAll() {
        return eventRepository.findAll();
    }

    public Optional<Event> getEventById(Long id) {
        return eventRepository.findById(id);
    }

    public Event addAlumno(AddAlumnoRequest request) {
        Event event = eventRepository.findById(request.getEventId()).orElseThrow(
                () -> new IllegalArgumentException("Evento no encontrado!"));
        Usuario alumno = userRepository.findById(request.getAlumnoId()).orElseThrow(
                () -> new IllegalArgumentException("Alumno no encontrado!"));
        Suscription suscripcion = suscriptionRepository.findByAlumnoId(alumno.getId()).orElseThrow(
                () -> new IllegalArgumentException("El alumno no tiene una suscripcion!"));
        String suscriptionState = suscripcion.getEstado().toString();

        if (suscriptionState.equals("ACTIVA")) {
            if (!event.getAlumnos().contains(alumno)) {
                if (event.getCuposDisponibles() > 0) {
                    if (alumno.getCantidadInscipciones() < suscripcion.getPaquete().getCantidadClases()) {
                        event.getAlumnos().add(alumno);
                        alumno.incremetarInscripciones();
                        event.setCuposDisponibles(event.getCuposDisponibles() - 1);
                        userRepository.save(alumno);
                        eventRepository.save(event);
                    } else {
                        throw new IllegalArgumentException(
                                "El alumno ha alcanzado el maximo de inscripciones permitidas por suscripcion!");
                    }
                } else {
                    throw new IllegalArgumentException("El evento esta lleno!");
                }
            } else {
                throw new IllegalArgumentException("El alumno ya esta inscrito a esta clase!");
            }
        } else {
            throw new IllegalArgumentException("El alumno no cuenta con una suscripcion activa!");
        }
        return event;
    }

    public Event removeAlumno(RemoveAlumnoRequest request) {
        Event event = eventRepository.findById(request.getEventId()).orElseThrow(
                () -> new RuntimeException("El evento no existe!"));

        Usuario alumno = userRepository.findById(request.getAlumnoId()).orElseThrow(
                () -> new RuntimeException("Usuario no encontrado!"));

        if (event.getAlumnos().contains(alumno)) {
            event.getAlumnos().remove(alumno);
            alumno.restarInscripciones();
            event.setCuposDisponibles(event.getCuposDisponibles() + 1);
            userRepository.save(alumno);
            return eventRepository.save(event);
        } else {
            throw new RuntimeException("El alumno no esta inscrito en el evento!");
        }
    }

    public Optional<List<Event>> getAlumnoEvents(int alumnoId) {
        return eventRepository.findByAlumnoId(alumnoId);
    }

    public void deleteEvent(Long eventId) {
        if (eventRepository.existsById(eventId)) {
            eventRepository.deleteById(eventId);
        } else {
            throw new RuntimeException("Evento no encontrado");
        }
    }

    @Transactional
    public void registrarAsistencia(Long eventId, List<Integer> alumnosAsistentesIds) {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new RuntimeException("Event not found"));
        for (Usuario alumno : event.getAlumnos()) {
            if (alumnosAsistentesIds.contains(alumno.getId())) {
                alumno.incrementarClasesAsistidas();
                userRepository.save(alumno);
            }
            // Verificar y actualizar el estado de la suscripción
            SuscriptionDTO alumnoSuscription = suscriptionService.getAlumnoSuscription(alumno.getId());
            if (alumno.getClasesAsistidas() >= alumnoSuscription.getPaquete().getCantidadClases()) {
                suscriptionService.actualizarEstado(alumnoSuscription.getId(), EstadoSuscripcion.INACTIVA);
                alumno.setClasesAsistidas(0);
            }

        }
    }
}
