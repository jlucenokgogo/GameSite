package game.example.server.service.impl;

import game.example.server.dto.AuthenticationDTO;
import game.example.server.dto.UserDTO;
import game.example.server.exceprion.MethodException;
import game.example.server.exceprion.ModelNoFound;
import game.example.server.model.Basket;
import game.example.server.model.ERole;
import game.example.server.model.User;
import game.example.server.repositories.UserRepository;
import game.example.server.security.JwtService;
import game.example.server.security.UserDetailsImpl;
import game.example.server.service.abstartion.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service("UserServiceImpl")
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    @Override
    public AuthenticationDTO registerNewUser(UserDTO registerRequest) throws MethodException {
        var user = User.builder()
                .username(registerRequest.getUsername())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .eRole(ERole.ROLE_USER)
                .basket(new Basket())
                .build();
        if (userRepository.existsUserByUsername(user.getUsername())) {
            throw new MethodException("User exists");
        }
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(UserDetailsImpl.build(user));
        log.info("generate jwt token and registration user");
        return AuthenticationDTO.builder()
                .token(jwtToken)
                .username(user.getUsername()).build();
    }

    @Override
    public AuthenticationDTO authentication(AuthenticationDTO authenticationDTO) throws ModelNoFound {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationDTO.getUsername(), authenticationDTO.getPassword())
        );
        var user = userRepository.findUserByUsername(authenticationDTO.getUsername())
                .orElseThrow(() -> new ModelNoFound("User not found"));
        var jwtToken = jwtService.generateToken(UserDetailsImpl.build(user));
        log.info("generate jwt token and authentication user");
        return AuthenticationDTO.builder()
                .token(jwtToken)
                .username(user.getUsername()).build();
    }
}
