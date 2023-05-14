package game.example.server.dto;

import game.example.server.model.ERole;
import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO implements Serializable {

    private Long idUser;

    private String username;

    private String password;

    private ERole eRole;
}
