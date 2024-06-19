package pe.oly.yoga_backend_api.Auth.Protected;

import java.util.List;

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
import pe.oly.yoga_backend_api.Schedule.Schedule;
import pe.oly.yoga_backend_api.Schedule.ScheduleDTO;
import pe.oly.yoga_backend_api.Schedule.ScheduleService;
import pe.oly.yoga_backend_api.Schedule.ScheduleUpdateResponse;

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

    @GetMapping("/alumno/{alumnoId}")
    public ResponseEntity<List<Schedule>> getSchedulesByAlumnoId(@PathVariable int alumnoId) {
        return ResponseEntity.ok(scheduleService.getSchedulesByAlumnoId(alumnoId));
    }

    @PutMapping("/update")
    public ResponseEntity<ScheduleUpdateResponse> updateSchedule(@RequestBody ScheduleDTO request) {
        ScheduleUpdateResponse response = scheduleService.updateSchedule(request);
        return ResponseEntity.ok(response);
    }
}
