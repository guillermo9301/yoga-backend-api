package pe.oly.yoga_backend_api.Auth.Protected;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import pe.oly.yoga_backend_api.Event.AddAlumnoRequest;
import pe.oly.yoga_backend_api.Event.CreateEventRequest;
import pe.oly.yoga_backend_api.Event.CreateEventResponse;
import pe.oly.yoga_backend_api.Event.Event;
import pe.oly.yoga_backend_api.Event.EventService;
import pe.oly.yoga_backend_api.Event.RemoveAlumnoRequest;
import pe.oly.yoga_backend_api.Event.UpdateEventResponse;

@RestController
@RequestMapping("api/event")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class EventController {
    private final EventService eventService;

    @PostMapping("/create")
    public ResponseEntity<CreateEventResponse> createEvent(@RequestBody CreateEventRequest request) {
        Event event = Event.builder()
                .fecha(request.getFecha())
                .horaInicio(request.getHoraInicio())
                .horaFin(request.getHoraFin())
                .capacidad(request.getCapacidad())
                .cuposDisponibles(request.getCapacidad())
                .recurrente(request.isRecurrente())
                .fechaFinRecurrencia(request.getFechaFinRecurrencia())
                .build();
        return ResponseEntity.ok(eventService.createEvent(event));
    }

    @PutMapping("update/{id}")
    public ResponseEntity<UpdateEventResponse> updateEvent(@PathVariable Long id, @RequestBody Event event) {
        UpdateEventResponse response = eventService.updateEvent(id, event);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public List<Event> getAllEvents() {
        return eventService.getAll();
    }

    @GetMapping("{id}")
    public Optional<Event> getEventById(@PathVariable Long id) {
        return eventService.getEventById(id);
    }

    @PostMapping("/addAlumno")
    public ResponseEntity<Event> addAlumno(@RequestBody AddAlumnoRequest request) {
        return ResponseEntity.ok(eventService.addAlumno(request));
    }

    @PostMapping("/removeAlumno")
    public ResponseEntity<Event> removeAlumno(@RequestBody RemoveAlumnoRequest request) {
        return ResponseEntity.ok(eventService.removeAlumno(request));
    }

    @GetMapping("alumno/{alumnoId}")
    public Optional<List<Event>> getByAlumnoId(@PathVariable int alumnoId) {
        return eventService.getAlumnoEvents(alumnoId);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteEventById(@PathVariable Long id) {
        try {
            eventService.deleteEvent(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
