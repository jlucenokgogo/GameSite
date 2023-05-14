package game.example.server.dto;

import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BasketDTO implements Serializable {

    private ArrayList<GameDTO> gameDTOS;

    private ArrayList<GameDLCDTO> gameDLCDTOS;

    private String email;

}
