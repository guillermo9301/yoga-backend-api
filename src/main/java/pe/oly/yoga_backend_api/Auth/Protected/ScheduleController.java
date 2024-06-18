package pe.oly.yoga_backend_api.Auth.Protected;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import pe.oly.yoga_backend_api.Schedule.ScheduleService;

@RestController
@RequestMapping("/api/schedule")
@RequiredArgsConstructor
@CrossOrigin(origins = { "http://localhost:4200" })

public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping(value = "book")
    public ResponseEntity<BookTrialResponse> bookSchedule(@RequestBody BookTrialRequest request) {
        return ResponseEntity.ok(scheduleService.createOrUpdateSchedule(request));
    }
}
