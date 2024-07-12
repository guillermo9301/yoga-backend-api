package pe.oly.yoga_backend_api.Suscription;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.oly.yoga_backend_api.Paquete.Paquete;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class updateSuscriptionRequest {
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Paquete paqueteId;
    private EstadoSuscripcion estado;
}
