package game.example.server.dto;

import game.example.server.model.DLCImage;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Set;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GameDLCDTO implements Serializable {

    private Long idGameDlc;

    private String dlsName;

    private Double priceDlc;

    private String youtube;

    private Long idGame;

    private ArrayList<DLCImageDTO> gameDlcImages;

}
