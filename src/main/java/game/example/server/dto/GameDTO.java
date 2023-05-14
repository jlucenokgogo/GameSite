package game.example.server.dto;

import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GameDTO implements Serializable {

    private Long idGame;

    private String gameName;

    private String youtube;

    private Double price;

    private ArrayList<GameImageDTO> gameImageDTOS;

    private GameInfoDTO gameInfoDTO;
}
