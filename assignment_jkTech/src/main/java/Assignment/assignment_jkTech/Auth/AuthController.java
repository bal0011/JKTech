package Assignment.assignment_jkTech.Auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        String token = "mock-jwt-token-for-" + request.getUsername();  
        return ResponseEntity.ok(new JwtResponse(token));
    }
}
