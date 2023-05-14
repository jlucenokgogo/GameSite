package game.example.server.service.abstartion;

import game.example.server.dto.AuthenticationDTO;
import game.example.server.dto.UserDTO;
import game.example.server.exceprion.MethodException;
import game.example.server.exceprion.ModelNoFound;

public interface UserService {

    AuthenticationDTO registerNewUser(UserDTO registerRequest) throws MethodException;
    AuthenticationDTO authentication(AuthenticationDTO authenticationRequest) throws ModelNoFound;

}
