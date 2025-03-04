package pe.oly.yoga_backend_api.Payment;

import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import pe.oly.yoga_backend_api.Paquete.PaqueteRepository;
import pe.oly.yoga_backend_api.Suscription.EstadoSuscripcion;
import pe.oly.yoga_backend_api.Suscription.Suscription;
import pe.oly.yoga_backend_api.Suscription.SuscriptionRepository;
import pe.oly.yoga_backend_api.Suscription.SuscriptionService;
import pe.oly.yoga_backend_api.Suscription.updateSuscriptionRequest;
import pe.oly.yoga_backend_api.User.UserRepository;
import pe.oly.yoga_backend_api.User.Usuario;
import pe.oly.yoga_backend_api.Paquete.Paquete;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;
    private final SuscriptionRepository suscriptionRepository;
    private final SuscriptionService suscriptionService;
    private final PaqueteRepository paqueteRepository;

    public Payment create(PaymentRequest request) {
        Usuario alumno = userRepository.findById(request.getAlumnoId()).orElseThrow(
                () -> new IllegalArgumentException("Usuario no encontrado!"));

        // se crea o se actualiza la suscripcion asociada al usuario autenticado
        Optional<Suscription> optionalSuscription = suscriptionRepository.findByAlumnoId(alumno.getId());

        if (optionalSuscription.isPresent()) {
            Suscription suscripcion = optionalSuscription.get();
            EstadoSuscripcion estado = suscripcion.getEstado();

            if (estado == EstadoSuscripcion.ACTIVA) {

                throw new IllegalStateException("El alumno todavia cuenta con una suscripción activa");

            } else if (estado == EstadoSuscripcion.INACTIVA) {
                Paquete paquete = paqueteRepository.findById(request.getPaqueteId()).orElseThrow(
                        () -> new IllegalArgumentException("El paquete indicado no existe!"));

                updateSuscriptionRequest renewedSuscription = updateSuscriptionRequest.builder()
                        .paqueteId(paquete)
                        .estado(EstadoSuscripcion.ACTIVA)
                        .build();

                suscriptionService.renewSuscription(suscripcion.getId(), renewedSuscription);
                return processPayment(request, alumno);
            }

        } else {
            Payment savedPayment = processPayment(request, alumno);

            Paquete paquete = paqueteRepository.findById(request.getPaqueteId()).orElseThrow(
                    () -> new IllegalArgumentException("El paquete indicado no existe!"));

            Suscription newSuscription = Suscription.builder()
                    .paquete(paquete)
                    .alumno(alumno)
                    .estado(EstadoSuscripcion.ACTIVA)
                    .build();

            suscriptionService.create(newSuscription);
            return savedPayment;
        }

        throw new IllegalStateException("Excepción inesperada!");
    }

    private Payment processPayment(PaymentRequest request, Usuario alumno) {
        Payment payment = Payment.builder()
                .usuario(alumno)
                .correo(request.getCorreo())
                .celular(request.getCelular())
                .numTarjeta(request.getNumTarjeta())
                .expiracion(request.getExpiracion())
                .cvc(request.getCvc())
                .titular(request.getTitular())
                .build();

        return paymentRepository.save(payment);
    }
}