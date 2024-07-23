package pe.oly.yoga_backend_api.Event;

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
public class CreateEventResponse {
    private String mensaje;
    private LocalDate fechaInicio;
    private LocalDate fechaFinRecurrencia;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private boolean recurrente;
}
