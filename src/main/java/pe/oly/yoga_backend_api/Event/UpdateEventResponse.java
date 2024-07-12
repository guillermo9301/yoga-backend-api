package pe.oly.yoga_backend_api.Event;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateEventResponse {
    private String mensaje;
    private Long eventoiId;
    private LocalDate fecha;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private int capacidad;
    private int cuposDisponibles;
}
