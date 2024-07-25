package pe.oly.yoga_backend_api.User;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    int id;
    String nombre;
    String apellido_paterno;
    String apellido_materno;
    String correo;
    LocalDate fec_nacimiento;
    int id_tipo_documento;
    String nro_documento;
    String celular;
    LocalDateTime fecha_registro;
    LocalDateTime fechaActualizacion;
    Integer clasesAsistidas;
    Long suscripcionId;
    int inscripciones;
    Rol rol;
}
