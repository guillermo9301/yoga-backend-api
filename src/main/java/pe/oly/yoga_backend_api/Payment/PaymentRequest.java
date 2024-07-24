package pe.oly.yoga_backend_api.Payment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequest {
    private String correo;
    private String celular;
    private String numTarjeta;
    private String expiracion;
    private String cvc;
    private String titular;
    private Integer alumnoId;
    private Long paqueteId;
}
