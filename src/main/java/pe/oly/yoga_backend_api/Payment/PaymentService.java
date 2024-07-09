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

    public Payment create(Payment request){

              Paquete paquete = paqueteRepository.findById(request.getPaqueteId().getId()).orElseThrow(
                                () -> new IllegalArgumentException("Paquete no encontrado!"));
        Payment payment = Payment.builder()
            .paqueteId(paquete)
            .correo(request.getCorreo())
            .celular(request.getCelular())
            .numTarjeta(request.getNumTarjeta())
            .expiracion(request.getExpiracion())
            .cvc(request.getCvc())
            .titular(request.getTitular()).build();


            Payment nuevoPago = paymentRepository.save(payment);

            return nuevoPago;

}
}