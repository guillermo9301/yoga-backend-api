package pe.oly.yoga_backend_api.Event;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import pe.oly.yoga_backend_api.User.UserRepository;
import pe.oly.yoga_backend_api.User.Usuario;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    public Event createEvent(Event request) {
        Event event = Event.builder()
                .fecha(request.getFecha())
                .horaInicio(request.getHoraInicio())
                .horaFin(request.getHoraFin())
                .capacidad(request.getCapacidad())
                .build();

        Event newEvent = eventRepository.save(event);

        return newEvent;
    }

    public UpdateEventResponse updateEvent(Long id, Event event) {
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

            // Se guarda el evento actualizado
            Event updatedEvent = eventRepository.save(existingEvent);
            return UpdateEventResponse.builder()
                    .mensaje("Se actualizó el evento satisfactoriamente")
                    .eventoiId(updatedEvent.getId())
                    .fecha(updatedEvent.getFecha())
                    .horaInicio(updatedEvent.getHoraInicio())
                    .horaFin(updatedEvent.getHoraFin())
                    .capacidad(updatedEvent.getCapacidad())
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

    public Event addAlumno(Long eventId, int alumnoId) {
        Event event = eventRepository.findById(eventId).orElseThrow(
                () -> new IllegalArgumentException("Evento no encontrado!"));
        Usuario alumno = userRepository.findById(alumnoId).orElseThrow(
                () -> new IllegalArgumentException("Alumno no encontrado!"));
        if (!event.getAlumnos().contains(alumno)) {
            event.getAlumnos().add(alumno);
            eventRepository.save(event);
        } else {
            throw new IllegalArgumentException("El alumno ya esta inscrito a esta clase!");
        }
        return event;
    }

    public Event removeAlumno(Long eventId, int alumnoId) {
        Event event = eventRepository.findById(eventId).orElseThrow(
                () -> new IllegalArgumentException("Evento no encontrado!"));
        Usuario alumno = userRepository.findById(alumnoId).orElseThrow(
                () -> new IllegalArgumentException("Alumno no encontrado!"));
        if (event.getAlumnos().contains(alumno)) {
            event.getAlumnos().remove(alumno);
            eventRepository.save(event);
        } else {
            throw new IllegalArgumentException("El alumno no está inscrito en la clase");
        }
        return event;
    }
}
