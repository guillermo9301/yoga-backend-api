package pe.oly.yoga_backend_api.TrialClass;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.oly.yoga_backend_api.Event.Event;
import pe.oly.yoga_backend_api.User.Usuario;

@Entity
@Builder
@Table(name = "trial_class")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TrialClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(targetEntity = Usuario.class)
    @JoinColumn(name = "id_alumno")
    private Usuario alumnoId;

    @OneToOne(targetEntity = Event.class)
    @JoinColumn(name = "evento_id")
    private Event eventoId;
}
