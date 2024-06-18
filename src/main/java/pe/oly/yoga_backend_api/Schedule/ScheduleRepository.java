package pe.oly.yoga_backend_api.Schedule;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    Optional<Schedule> findByFechaAndHoraInicioAndHoraFin(LocalDate fecha, LocalTime hora_inicio, LocalTime hora_fin);
}
