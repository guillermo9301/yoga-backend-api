package pe.oly.yoga_backend_api.Schedule;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.oly.yoga_backend_api.User.Usuario;

@Entity
@Builder
@Table(name = "schedule")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDate fecha;
    @Column(name = "hora_inicio")
    private LocalTime horaInicio;

    @Column(name = "hora_fin")
    private LocalTime horaFin;
    private String correo;
    private String nombre_alumno;
    private String apellido_paterno_alumno;
    private String apellido_materno_alumno;

    @ManyToMany(targetEntity = Usuario.class, fetch = FetchType.LAZY)
    @JoinTable(name = "schedule_alumnos", joinColumns = @JoinColumn(name = "schedule_id"), inverseJoinColumns = @JoinColumn(name = "alumno_id"))
    private List<Usuario> alumnos;
}
