package game.example.server.service.abstartion;

import game.example.server.dto.GameDLCDTO;
import game.example.server.dto.GameDTO;

import java.util.HashMap;

public interface BasketStatistic {

    Double allProfitByAllUser();

    HashMap<GameDTO,Double> allProfitByOneGame();

    HashMap<GameDLCDTO,Double> allProfitByOneGameDLC();
}
