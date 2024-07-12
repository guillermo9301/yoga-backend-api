package pe.oly.yoga_backend_api.User;

import java.sql.Date;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    int id;
    String nombre;
    String apellido_paterno;
    String apellido_materno;
    String correo;
    LocalDate fec_nacimiento;
    int id_tipo_documento;
    String nro_documento;
    String celular;
    Rol rol;
}
