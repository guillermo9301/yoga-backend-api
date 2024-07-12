package pe.oly.yoga_backend_api.Event;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Data;

@Data
public class CreateEventRequest {
    private LocalDate fecha;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private int capacidad;
    private boolean recurrente;
    private LocalDate fechaFinRecurrencia;
}
