package pe.oly.yoga_backend_api.Auth.Public;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterResponse {
    int id;
    String nombre;
    String correo;
    String apellido_paterno;
    String apellido_materno;
    String rol;
}
