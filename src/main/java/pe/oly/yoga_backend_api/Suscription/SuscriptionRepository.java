package pe.oly.yoga_backend_api.Suscription;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;
import pe.oly.yoga_backend_api.Paquete.Paquete;

public interface SuscriptionRepository extends JpaRepository<Suscription, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE Suscription s SET s.paquete = :paquete, s.fechaInicio = :fechaInicio, s.fechaFin = :fechaFin, s.estado = :estado WHERE s.id = :id")
    int renewSuscription(@Param("id") Long id, @Param("paquete") Paquete paquete,
            @Param("fechaInicio") LocalDate fechaInicio, @Param("fechaFin") LocalDate fechaFin,
            @Param("estado") EstadoSuscripcion estado);

    Optional<Suscription> findByAlumnoId(Integer alumnoId);

}
