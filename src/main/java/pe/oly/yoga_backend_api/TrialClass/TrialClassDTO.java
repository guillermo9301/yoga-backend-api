package pe.oly.yoga_backend_api.TrialClass;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.oly.yoga_backend_api.User.Usuario;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class TrialClassDTO {
    int id;
    LocalDate fecha;
    LocalTime hora_inicio;
    LocalTime hora_fin;
    String correo;
    Usuario id_alumno;
    String nombre_alumno;
    String apellido_paterno_alumno;
    String apellido_materno_alumno;
}
