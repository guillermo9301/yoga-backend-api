package pe.oly.yoga_backend_api.Auth.Protected;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import pe.oly.yoga_backend_api.User.UserDTO;
import pe.oly.yoga_backend_api.User.UserRequest;
import pe.oly.yoga_backend_api.User.UserResponse;
import pe.oly.yoga_backend_api.User.UserService;
import pe.oly.yoga_backend_api.User.Usuario;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@CrossOrigin(origins = { "http://localhost:4200", "https://oly-webapp.netlify.app/" })
public class UserController {
    private final UserService userService;

    @GetMapping(value = "{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Integer id) {
        UserDTO userDTO = userService.getUser(id);
        if (userDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userDTO);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Integer id, @RequestBody UserRequest userRequest)
            throws Exception {
        return ResponseEntity.ok(userService.updateUser(id, userRequest));
    }

    @GetMapping
    public List<Usuario> getAllUsers() {
        return userService.getAll();
    }

}
