package game.example.server.service.impl;

import game.example.server.dto.GameDLCDTO;
import game.example.server.dto.GameDTO;
import game.example.server.mappers.GameDLCMapper;
import game.example.server.mappers.GameMapper;
import game.example.server.model.Basket;
import game.example.server.model.Game;
import game.example.server.model.GameDLC;
import game.example.server.repositories.BasketRepository;
import game.example.server.repositories.GameDLCRepository;
import game.example.server.repositories.GameRepository;
import game.example.server.service.abstartion.BasketStatistic;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Slf4j
@Service("StatisticGameFacadeImpl")
@RequiredArgsConstructor
public class BasketStatisticImpl implements BasketStatistic {

    private final GameMapper gameMapper;

    private final GameRepository gameRepository;

    private final GameDLCMapper gameDLCMapper;

    private final GameDLCRepository gameDLCRepository;

    private final BasketRepository basketRepository;

    @Override
    public Double allProfitByAllUser() {
        double profit = 0.0;
        var listBaskets = basketRepository.findAll();
        for (Basket basket : listBaskets) {
            for (Game g : basket.getGames()) {
                profit = profit + g.getPrice();
            }
            for (GameDLC g : basket.getGameDLCS()) {
                profit = profit + g.getPriceDlc();
            }
        }
        return profit;
    }

    @Override
    public HashMap<GameDTO, Double> allProfitByOneGame() {
        HashMap<GameDTO, Double> res = new HashMap<>();
        var listBaskets = basketRepository.findAll();
        var listGame = gameRepository.findAll();
        for (Game g : listGame) {
            double profitByGame = 0.0;
            for (Basket basket : listBaskets) {
                for (Game gg : basket.getGames()) {
                    if (gg.equals(g)) {
                        profitByGame = profitByGame + gg.getPrice();
                        break;
                    }
                }
            }
            res.put(gameMapper.INSTANCE.toDTO_fromModel(g), profitByGame);
        }
        return res;
    }

    @Override
    public HashMap<GameDLCDTO, Double> allProfitByOneGameDLC() {
        HashMap<GameDLCDTO, Double> res = new HashMap<>();
        var listBaskets = basketRepository.findAll();
        var listGame = gameDLCRepository.findAll();
        for (GameDLC g : listGame) {
            double profitByGame = 0.0;
            for (Basket basket : listBaskets) {
                for (GameDLC gg : basket.getGameDLCS()) {
                    if (gg.equals(g)) {
                        profitByGame = profitByGame + gg.getPriceDlc();
                        break;
                    }
                }
            }
            res.put(gameDLCMapper.INSTANCE.toDTO_fromModel(g), profitByGame);
        }
        return res;
    }
}
