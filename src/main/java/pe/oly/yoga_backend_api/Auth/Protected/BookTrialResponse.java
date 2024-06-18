package pe.oly.yoga_backend_api.Auth.Protected;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookTrialResponse {
    int id;
    LocalDate fecha;
    LocalTime hora_inicio;
    LocalTime hora_fin;
    int id_alumno;
    String mensaje;
 
    public BookTrialResponse(String mensaje) {
        this.mensaje = mensaje;
    }
}
