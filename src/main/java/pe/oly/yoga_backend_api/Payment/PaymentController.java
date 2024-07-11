package pe.oly.yoga_backend_api.Payment;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/pago")
@RequiredArgsConstructor
@CrossOrigin(origins = { "http://localhost:4200", "yoga-project-webapp-production.up.railway.app" })
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/nuevoPago")
    public Payment create(@RequestBody Payment payment) {
        return paymentService.create(payment);
    }

}
