package finances.user;

import finances.model.User;
import finances.security.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<Void> signup(@RequestBody User user) {
        log.trace("Enter signup");
        userService.signup(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("hello");
    }
}
