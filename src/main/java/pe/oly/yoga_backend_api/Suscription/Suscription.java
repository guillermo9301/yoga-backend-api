package pe.oly.yoga_backend_api.Suscription;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.oly.yoga_backend_api.Paquete.Paquete;
import pe.oly.yoga_backend_api.User.Usuario;

import java.time.LocalDate;

@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Suscripcion")
public class Suscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fechaInicio;

    private LocalDate fechaFin;

    @ManyToOne
    @JoinColumn(name = "id_paquete", referencedColumnName = "id")
    private Paquete paquete;

    @OneToOne
    @JoinColumn(name = "id_alumno", referencedColumnName = "id")
    private Usuario alumno;

    @Enumerated(EnumType.STRING)
    EstadoSuscripcion estado;

    @PrePersist
    void prePersist() {
        fechaInicio = LocalDate.now();
        fechaFin = fechaInicio.plusDays(paquete.getCantidadDias());
    }

    @PreUpdate
    void preUpdate() {
        fechaInicio = LocalDate.now();
        fechaFin = fechaInicio.plusDays(paquete.getCantidadDias());
    }
}
