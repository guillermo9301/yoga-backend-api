package pe.oly.yoga_backend_api.CustomValidations;

import java.time.LocalDate;
import java.time.Period;

import org.springframework.stereotype.Service;

@Service
public class AgeValidationService {
    public void validateMinAge(LocalDate birthDate, int minAge) {
        if (Period.between(birthDate, LocalDate.now()).getYears() < minAge) {
            throw new IllegalArgumentException("La edad minima debe ser " + minAge + " años");
        }
    }
}
