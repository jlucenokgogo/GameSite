package game.example.server.service.abstartion;

import game.example.server.dto.GameDTO;
import game.example.server.exceprion.ModelNoFound;
import game.example.server.service.baseabstartion.AsyncCrud;
import game.example.server.service.baseabstartion.CrudServiceDTO;

import java.util.HashMap;

public interface GameService extends CrudServiceDTO<GameDTO>, AsyncCrud<GameDTO> {

    HashMap<GameDTO,Boolean> readOneModelByExistToBasket(Long idGame,String username) throws ModelNoFound;

}
