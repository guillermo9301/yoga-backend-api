package pe.oly.yoga_backend_api.Event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddAlumnoRequest {
    private Long eventId;
    private int alumnoId;
}
