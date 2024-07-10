package pe.oly.yoga_backend_api.Paquete;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import pe.oly.yoga_backend_api.Payment.Payment;
import pe.oly.yoga_backend_api.Payment.PaymentRepository;

@RequiredArgsConstructor
@Service
public class PaqueteService {

    private final PaqueteRepository paqueteRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    public void deleteById(Long id) {
        // Buscar el paquete por ID
        Paquete paquete = paqueteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paquete no encontrado con ID: " + id));

        // Buscar el payment asociado al paquete
        Payment payment = paymentRepository.findByPaqueteId(paquete);

        // Si hay un payment asociado, eliminarlo
        if (payment != null) {
            paymentRepository.delete(payment);
        }

        // Eliminar el paquete
        paqueteRepository.deleteById(id);
    }

    public Paquete create(Paquete request) {
        Paquete paquete = Paquete.builder()
                .nombre(request.getNombre())
                .precio(request.getPrecio())
                .cantidadClases(request.getCantidadClases())
                .cantidadDias(request.getCantidadDias()).build();

        Paquete nuevoPaquete = paqueteRepository.save(paquete);

        return nuevoPaquete;
    }

    public List<Paquete> getAll() {
        return paqueteRepository.findAll();
    }

    public Paquete update(Long id, Paquete request) {
        Optional<Paquete> optionalPaquete = paqueteRepository.findById(id);

        if (optionalPaquete.isPresent()) {
            Paquete paqueteExistente = optionalPaquete.get();
            paqueteExistente.setNombre(request.getNombre());
            paqueteExistente.setPrecio(request.getPrecio());
            paqueteExistente.setCantidadClases(request.getCantidadClases());
            paqueteExistente.setCantidadDias(request.getCantidadDias());

            return paqueteRepository.save(paqueteExistente);
        } else {
            // Manejo de excepción si no se encuentra el paquete con el ID proporcionado
            throw new RuntimeException("Paquete no encontrado con el ID: " + id);
        }
    }

}
