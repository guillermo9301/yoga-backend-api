package pe.oly.yoga_backend_api.Auth.Protected;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
}
