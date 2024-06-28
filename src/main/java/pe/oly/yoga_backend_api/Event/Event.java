package pe.oly.yoga_backend_api.Event;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.oly.yoga_backend_api.User.Usuario;

@Data
@Entity
@Builder
@Table(name = "evento")
@AllArgsConstructor
@NoArgsConstructor
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fecha;

    @Column(name = "hora_inicio")
    private LocalTime horaInicio;

    @Column(name = "hora_fin")
    private LocalTime horaFin;

    private int capacidad;

    @ManyToMany(targetEntity = Usuario.class, fetch = FetchType.LAZY)
    @JoinTable(name = "horario_alumno", joinColumns = @JoinColumn(name = "evento_id"), inverseJoinColumns = @JoinColumn(name = "alumno_id"))
    private List<Usuario> alumnos;

}
