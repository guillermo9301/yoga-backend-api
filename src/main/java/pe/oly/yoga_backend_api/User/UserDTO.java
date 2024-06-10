package pe.oly.yoga_backend_api.User;

import java.sql.Date;

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
    Date fec_nacimiento;
    int id_tipo_documento;
    String nro_documento;
    String celular;
    Date fecha_registro;
    Rol rol;
}
