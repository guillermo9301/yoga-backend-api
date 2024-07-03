package pe.oly.yoga_backend_api.Suscription;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/suscripcion")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class SuscriptionController {

    private final SuscriptionService suscriptionService;

    @PostMapping("/nueva")
    public Suscription create (@RequestBody Suscription suscription){
        return suscriptionService.create(suscription);
    }
}
