package game.example.server.dto;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationDTO {

    private String username;

    private String token;

    private String password;
}
