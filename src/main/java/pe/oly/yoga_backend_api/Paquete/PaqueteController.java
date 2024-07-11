package pe.oly.yoga_backend_api.Paquete;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/paquete")
@RequiredArgsConstructor
@CrossOrigin(origins = { "http://localhost:4200", "yoga-project-webapp-production.up.railway.app" })
public class PaqueteController {

  private final PaqueteService paqueteService;

  @PostMapping("/nuevoPaquete")
  public Paquete create(@RequestBody Paquete paquete) {
    return paqueteService.create(paquete);
  }

  @GetMapping("/{id}")
  public Paquete getPaqueteById(@PathVariable Long id) {
    return paqueteService.getPaqueteById(id);
  }

  @GetMapping
  public List<Paquete> getAllPaquetes() {
    return paqueteService.getAll();
  }

  /*
   * @DeleteMapping("/{id}")
   * public void deletePaquete(@PathVariable Long id) {
   * paqueteService.deleteById(id);
   * }
   */

  @PutMapping("/{id}")
  public Paquete updatePaquete(@PathVariable Long id, @RequestBody Paquete paquete) {
    return paqueteService.update(id, paquete);
  }
}
