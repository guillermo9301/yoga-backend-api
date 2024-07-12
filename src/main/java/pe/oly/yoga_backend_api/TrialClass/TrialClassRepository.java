package pe.oly.yoga_backend_api.TrialClass;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TrialClassRepository extends JpaRepository<TrialClass, Integer> {

    @Query("SELECT t FROM TrialClass t WHERE t.alumnoId.id = :alumnoId")
    Optional<TrialClass> findByAlumno_Id(int alumnoId);
}
