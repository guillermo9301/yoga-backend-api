package pe.oly.yoga_backend_api.Event;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EventRepository extends JpaRepository<Event, Long> {

    @Query("SELECT e FROM Event e JOIN e.alumnos a WHERE a.id = :alumnoId")
    Optional<List<Event>> findByAlumnoId(int alumnoId);
}
