package pe.oly.yoga_backend_api.Paquete;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PaqueteService {

    private final PaqueteRepository paqueteRepository;

    public Paquete create(Paquete request){
        Paquete paquete = Paquete.builder()
            .nombre(request.getNombre())
            .precio(request.getPrecio())
            .cantidadClases(request.getCantidadClases())
            .cantidadDias(request.getCantidadDias()).build();

            Paquete nuevoPaquete = paqueteRepository.save(paquete);

            return nuevoPaquete;
    }
}
