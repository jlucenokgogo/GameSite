package game.example.server.service.abstartion;

import game.example.server.dto.GameImageDTO;
import game.example.server.exceprion.ModelNoFound;
import game.example.server.model.GameImage;
import game.example.server.service.baseabstartion.CrudServiceDTO;

import java.util.ArrayList;
import java.util.List;

public interface GameImageService extends CrudServiceDTO<GameImageDTO> {

    List<GameImageDTO> updateListImage(ArrayList<GameImageDTO> gameImageDTOS,Long id) throws ModelNoFound;

}
