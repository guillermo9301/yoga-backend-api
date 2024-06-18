package pe.oly.yoga_backend_api.TrialClass;

import java.time.LocalDate;

import java.time.LocalTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;



public interface TrialClassRepository extends JpaRepository<TrialClass, Integer> {
    Optional<TrialClass> findById(int id);

    /*  @Modifying()
    @Query("update trial_class c set c.fecha=:fecha, c.horario_inicio=:horario_inicio, c.horario_fin=:horario_fin, ")
    void updateTrialClass(
            @Param(value = "id") Integer id,
            @Param(value = "horario_inicio") LocalTime horario_inicio,
            @Param(value = "horario_fin") LocalTime horario_fin,
            @Param(value = "fecha") LocalDate fecha); */
    
}

