package pe.oly.yoga_backend_api.Auth.Public;

import java.time.LocalDateTime;

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
    int id;
    String nombre;
    String correo;
    String apellido_paterno;
    String apellido_materno;
    LocalDateTime fecha_registro;
    String rol;
}
