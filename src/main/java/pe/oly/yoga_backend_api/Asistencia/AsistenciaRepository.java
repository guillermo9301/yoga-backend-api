package pe.oly.yoga_backend_api.Asistencia;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AsistenciaRepository extends JpaRepository<Asistencia, Long> {
    List<Asistencia> findByEventoId(Long eventoId);
}
