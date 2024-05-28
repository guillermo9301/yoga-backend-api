package pe.oly.yoga_backend_api.Auth.Protected;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    @PostMapping(value = "adminDashboard")
    public String welcome() {
        return "Endpoint protegido 1";
    }

}
