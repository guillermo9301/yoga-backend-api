package pe.oly.yoga_backend_api.Schedule;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    Optional<Schedule> findByFechaAndHoraInicioAndHoraFin(LocalDate fecha, LocalTime hora_inicio, LocalTime hora_fin);

    @Query("SELECT s FROM Schedule s JOIN s.alumnos a WHERE a.id = :alumnoId")
    List<Schedule> findAllByAlumnoId(@Param("alumnoId") int alumnoId);
}
