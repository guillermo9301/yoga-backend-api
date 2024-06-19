package pe.oly.yoga_backend_api.Auth.Protected;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
import pe.oly.yoga_backend_api.TrialClass.TrialClass;
import pe.oly.yoga_backend_api.TrialClass.TrialClassService;



@RestController
@RequestMapping("/api/trial_class")
@RequiredArgsConstructor
@CrossOrigin(origins = { "http://localhost:4200" })

public class TrialClassController {
    private final TrialClassService trialClassService;

    @PostMapping(value = "book")
    public ResponseEntity<BookTrialResponse> bookTrial(@RequestBody BookTrialRequest request) {
        return ResponseEntity.ok(trialClassService.book(request));
    }

      @GetMapping
    public List<TrialClass> getAllTrials() {
        return trialClassService.getAll();
    }

       @PutMapping(value = "{id}")
    public ResponseEntity<BookTrialResponse> updateTrialClass(@PathVariable Integer id, @RequestBody BookTrialRequest request)
            throws Exception {
        return ResponseEntity.ok(trialClassService.updateTrialClass(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrialClass(@PathVariable Integer id) {
        try {
            trialClassService.deleteTrialClass(id);
            return ResponseEntity.noContent().build();
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

}
