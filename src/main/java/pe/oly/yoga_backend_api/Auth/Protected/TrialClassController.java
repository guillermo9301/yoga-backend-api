package pe.oly.yoga_backend_api.Auth.Protected;

import java.util.List;
import java.util.Map;

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
import pe.oly.yoga_backend_api.TrialClass.TrialClass;
import pe.oly.yoga_backend_api.TrialClass.TrialClassDTO;
import pe.oly.yoga_backend_api.TrialClass.TrialClassService;
import pe.oly.yoga_backend_api.TrialClass.UpdateTrialClassResponse;

@RestController
@RequestMapping("api/trial_class")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class TrialClassController {
    private final TrialClassService trialClassService;

    @PostMapping("new_trial_class")
    public ResponseEntity<TrialClass> bookTrial(@RequestBody TrialClass trialClass) {
        return ResponseEntity.ok(trialClassService.createTrialClass(trialClass));
    }

    @PutMapping("update/{trialClassId}")
    public ResponseEntity<UpdateTrialClassResponse> updateTrialClass(@PathVariable int trialClassId,
            @RequestBody Map<String, Long> request) {
        Long newEventId = request.get("newEventId");
        return ResponseEntity.ok(trialClassService.updateTrialClass(trialClassId, newEventId));
    }

    @GetMapping("byAlumno/{alumnoId}")
    public ResponseEntity<TrialClassDTO> getTrialClassByAlumnoId(@PathVariable int alumnoId) {
        return ResponseEntity.ok(trialClassService.geTrialClassByAlumnoId(alumnoId));
    }

    @GetMapping("listAll")
    public ResponseEntity<List<TrialClassDTO>> getAllTrialClasses() {
        return ResponseEntity.ok(trialClassService.getAllTrialClasses());
    }
}
