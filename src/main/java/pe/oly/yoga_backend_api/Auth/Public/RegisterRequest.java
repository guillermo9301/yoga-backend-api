package pe.oly.yoga_backend_api.Auth.Public;

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
public class RegisterRequest {
    String correo;
    String password;
    String nombre;
    String apellido_paterno;
    String apellido_materno;
    LocalDate fec_nacimiento;
    Integer id_tipo_documento;
    String nro_documento;
    String celular;
    LocalDateTime fecha_registro;
}
