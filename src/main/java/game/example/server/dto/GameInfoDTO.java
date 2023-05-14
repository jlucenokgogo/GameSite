package game.example.server.dto;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GameInfoDTO {

    private Long idGameInfo;

    private String desc;

    private String newM;

    private Long idGame;

}
