package pe.oly.yoga_backend_api.CustomValidations;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.stereotype.Service;

@Service
public class EventValidationService {
    public void validateEventDates(LocalDate eventDate) {
        if (eventDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha del evento debe ser una fecha futura.");
        }
    }

    public void validateEventTimes(LocalDate eventDate, LocalTime startTime, LocalTime endTime) {
        if (startTime.isAfter(endTime)) {
            throw new IllegalArgumentException("La hora de inicio debe ser anterior a la hora de fin.");
        }

        LocalDateTime eventStartDateTime = LocalDateTime.of(eventDate, startTime);
        LocalDateTime currentDateTime = LocalDateTime.now();

        if (eventStartDateTime.isBefore(currentDateTime)) {
            throw new IllegalArgumentException("La hora de inicio no puede ser anterior a la hora actual.");
        }
    }
}
