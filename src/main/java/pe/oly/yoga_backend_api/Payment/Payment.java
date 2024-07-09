package pe.oly.yoga_backend_api.Payment;



import jakarta.persistence.Column;
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
import pe.oly.yoga_backend_api.Paquete.Paquete;

@Entity
@Builder
@Table(name = "payment")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String correo;

    @Column(nullable = false)
    private String celular;

    @Column(nullable = false , name = "num_tarjeta")
    private String numTarjeta;

    @Column(nullable = false)
    private String expiracion;

    @Column(nullable = false)
    private String cvc;

    @Column(nullable = false)
    private String titular;



    @OneToOne(targetEntity = Paquete.class)
    @JoinColumn(name = "paquete")
    private Paquete paqueteId;

}
