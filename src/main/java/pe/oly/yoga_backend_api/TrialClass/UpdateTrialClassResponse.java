package pe.oly.yoga_backend_api.TrialClass;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTrialClassResponse {
    private LocalDate fecha;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private String mensaje;
}
