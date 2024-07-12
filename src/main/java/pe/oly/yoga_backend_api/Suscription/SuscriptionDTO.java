package pe.oly.yoga_backend_api.Suscription;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.oly.yoga_backend_api.Paquete.Paquete;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SuscriptionDTO {
    private Long id;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Paquete paquete;
    private EstadoSuscripcion estado;
}
