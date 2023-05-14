package game.example.server.rest;

import game.example.server.dto.AuthenticationDTO;
import game.example.server.dto.UserDTO;
import game.example.server.exceprion.MethodException;
import game.example.server.exceprion.ModelNoFound;
import game.example.server.service.abstartion.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/api/users")
@RestController
@RequiredArgsConstructor
@CrossOrigin
public class UserRestController {

    private final UserService authenticationService;

    @PostMapping("/authorization")
    public ResponseEntity<AuthenticationDTO> authorization(
            @ModelAttribute AuthenticationDTO authenticationDTO) throws  ModelNoFound {
        return new ResponseEntity<>
                (authenticationService.authentication(authenticationDTO), HttpStatus.OK);
    }

    @PostMapping("/registrationNewUser")
    public ResponseEntity<AuthenticationDTO> registrationNewUser(
            @ModelAttribute UserDTO registerRequest) throws MethodException {
        return new ResponseEntity<>
                (authenticationService.registerNewUser(registerRequest), HttpStatus.CREATED);
    }
}
