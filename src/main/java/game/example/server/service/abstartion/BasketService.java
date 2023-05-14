package game.example.server.service.abstartion;

import game.example.server.dto.BasketDTO;
import game.example.server.dto.GameDLCDTO;
import game.example.server.dto.GameDTO;
import game.example.server.exceprion.ModelNoFound;
import game.example.server.service.baseabstartion.CrudServiceDTO;

public interface BasketService{
    GameDTO addGameToBasket(String username, Long idGame) throws ModelNoFound;

    void putAwayGameFromBasket(String username, Long idGame) throws ModelNoFound;

    GameDLCDTO addGameDLCToBasket(String username, Long idGame, Long idDlc) throws ModelNoFound;

    void putAwayGameDLCFromBasket(String username, Long idGame, Long idDlc) throws ModelNoFound;

    void clearAllBasket(String username) throws ModelNoFound;

    BasketDTO readOneBasketByUsername(String username) throws ModelNoFound;

}
