package pe.oly.yoga_backend_api.Suscription;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/suscripcion")
@RequiredArgsConstructor
@CrossOrigin(origins = { "http://localhost:4200", "yoga-project-webapp-production.up.railway.app" })
public class SuscriptionController {

    private final SuscriptionService suscriptionService;

    @PostMapping("/nueva")
    public SuscriptionDTO create(@RequestBody Suscription suscription) {
        return suscriptionService.create(suscription);
    }

    @PutMapping("/renovar/{id}")
    public ResponseEntity<Void> renewSuscription(@PathVariable Long id, @RequestBody updateSuscriptionRequest request) {
        suscriptionService.renewSuscription(id, request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{alumnoId}")
    public SuscriptionDTO getAlumnoSuscription(@PathVariable int alumnoId) {
        return suscriptionService.getAlumnoSuscription(alumnoId);
    }
}
