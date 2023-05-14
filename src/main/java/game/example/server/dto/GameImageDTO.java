package game.example.server.dto;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GameImageDTO implements Serializable {

    private Long idImage;

    private String url;

    private Long idGame;
}
