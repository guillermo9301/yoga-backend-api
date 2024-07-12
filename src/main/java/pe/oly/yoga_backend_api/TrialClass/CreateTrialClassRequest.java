package pe.oly.yoga_backend_api.TrialClass;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.oly.yoga_backend_api.Event.Event;
import pe.oly.yoga_backend_api.User.Usuario;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateTrialClassRequest {
    private Usuario id_alumno;
    private Event id_evento;

}
