package pe.oly.yoga_backend_api.Suscription;


import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import pe.oly.yoga_backend_api.Paquete.Paquete;
import pe.oly.yoga_backend_api.Paquete.PaqueteRepository;
import pe.oly.yoga_backend_api.User.UserRepository;
import pe.oly.yoga_backend_api.User.Usuario;



@Service
@RequiredArgsConstructor
public class SuscriptionService {

    private final SuscriptionRepository suscriptionRepository;
    private final PaqueteRepository paqueteRepository;
    private final UserRepository userRepository;

    public Suscription create(Suscription request){
        Paquete paquete = paqueteRepository.findById(request.getPaquete().getId()).orElseThrow(
                () -> new IllegalArgumentException("Paquete no encontrado!"));
        Usuario alumno = userRepository.findById(request.getAlumno().getId()).orElseThrow(
                () -> new IllegalArgumentException("Alumno no encontrado!"));

        Suscription suscription = Suscription.builder()
            .fechaHora(null)
            .alumno(alumno)
            .paquete(paquete)
            .estado("activo").build();

        Suscription nuevaSuscription = suscriptionRepository.save(suscription);

        return nuevaSuscription;
    }
    
}
