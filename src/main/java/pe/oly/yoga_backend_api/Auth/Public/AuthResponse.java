package pe.oly.yoga_backend_api.Auth.Public;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    String token;
    String nombre;
    String correo;
    String apellido_paterno;
    String rol;
}
