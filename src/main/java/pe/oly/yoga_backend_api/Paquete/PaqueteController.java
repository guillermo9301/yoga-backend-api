package pe.oly.yoga_backend_api.Paquete;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/paquete")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class PaqueteController {

    private final PaqueteService paqueteService;

    @PostMapping("/nuevoPaquete")
    public Paquete create (@RequestBody Paquete paquete){
        return paqueteService.create(paquete);
    }
}
