package pe.oly.yoga_backend_api.TrialClass;

import java.time.LocalDate;
import java.time.LocalTime;

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
    private Long id;

    private LocalDate fecha;
    private LocalTime hora_inicio;
    private LocalTime hora_fin;
    private String correo;
    private String nombre_alumno;
    private String apellido_paterno_alumno;
    private String apellido_materno_alumno;

    @OneToOne(targetEntity = Usuario.class)
    @JoinColumn(name = "id_alumno")
    private Usuario alumno;
}
