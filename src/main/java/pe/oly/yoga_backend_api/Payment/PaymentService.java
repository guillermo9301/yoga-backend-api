package pe.oly.yoga_backend_api.Payment;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import pe.oly.yoga_backend_api.Paquete.Paquete;
import pe.oly.yoga_backend_api.Paquete.PaqueteRepository;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaqueteRepository paqueteRepository;

    public Payment create(Payment request) {
        Payment payment = Payment.builder()
                .paqueteId(request.getPaqueteId())
                .correo(request.getCorreo())
                .celular(request.getCelular())
                .numTarjeta(request.getNumTarjeta())
                .expiracion(request.getExpiracion())
                .cvc(request.getCvc())
                .titular(request.getTitular()).build();

        return paymentRepository.save(payment);

    }
}