package pe.oly.yoga_backend_api.TrialClass;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TrialClassRepository extends JpaRepository<TrialClass, Integer> {
    Optional<TrialClass> findById(int id);
}
