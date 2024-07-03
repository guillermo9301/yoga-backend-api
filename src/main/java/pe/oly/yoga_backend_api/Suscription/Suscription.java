package pe.oly.yoga_backend_api.Suscription;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import pe.oly.yoga_backend_api.Paquete.Paquete;
import pe.oly.yoga_backend_api.User.Usuario;

import java.time.LocalDateTime;

@Builder
@Data
@Entity
@Table(name = "Suscripcion")
public class Suscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    private LocalDateTime fechaHora;

    @OneToOne
    @JoinColumn(name = "id_paquete", referencedColumnName = "id")
    private Paquete paquete;

    @OneToOne
    @JoinColumn(name = "id_alumno", referencedColumnName = "id")
    private Usuario alumno;

    @Column(nullable = false)
    private String estado;
}

