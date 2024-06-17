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
public class BookTrialRequest {
    LocalDate fecha;
    LocalTime hora_inicio;
    LocalTime hora_fin;
    String correo;
    int id_alumno;
    String nombre_alumno;
    String apellido_paterno_alumno;
    String apellido_materno_alumno;
}
