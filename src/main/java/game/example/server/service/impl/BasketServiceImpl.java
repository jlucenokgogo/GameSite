package game.example.server.service.impl;

import game.example.server.dto.BasketDTO;
import game.example.server.dto.GameDLCDTO;
import game.example.server.dto.GameDTO;
import game.example.server.exceprion.ModelExist;
import game.example.server.exceprion.ModelNoFound;
import game.example.server.mappers.GameDLCMapper;
import game.example.server.mappers.GameMapper;
import game.example.server.model.Game;
import game.example.server.model.GameDLC;
import game.example.server.repositories.BasketRepository;
import game.example.server.repositories.GameDLCRepository;
import game.example.server.repositories.GameRepository;
import game.example.server.service.abstartion.BasketService;
import game.example.server.utils.technical.commands.CommandExecute;
import game.example.server.utils.technical.commands.CommandGetting;
import game.example.server.utils.technical.commands.ReadCommandModel;
import game.example.server.utils.technical.iterators.LoadingIterator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;

@Slf4j
@Service("BasketServiceImpl")
@RequiredArgsConstructor
public class BasketServiceImpl implements BasketService {

    private final BasketRepository basketRepository;

    private final ReadCommandModel<GameRepository, Game> gameReadCommandModel;

    private final ReadCommandModel<GameDLCRepository, GameDLC> gameDLCReadCommandModel;

    private final GameMapper gameMapper;

    private final GameDLCMapper gameDLCMapper;

    @Override
    public GameDTO addGameToBasket(String username, Long idGame) throws ModelNoFound {
        var basket = basketRepository.findBasketByUser_Username(username)
                .orElseThrow(() -> new ModelNoFound("basket no found"));
        var game = gameReadCommandModel.execute(idGame);
        if (basket.getGames() == null) {
            basket.setGames(new HashSet<>());
        }
        basket.getGames().add(game);
        basketRepository.save(basket);
        return gameMapper.INSTANCE.toDTO_fromModel(game);
    }

    @Override
    public void putAwayGameFromBasket(String username, Long idGame) throws ModelNoFound {
        var basket = basketRepository.findBasketByUser_Username(username)
                .orElseThrow(() -> new ModelNoFound("basket no found"));
        var game = gameReadCommandModel.execute(idGame);
        basket.getGames().remove(game);
        basketRepository.save(basket);
    }

    @Override
    public GameDLCDTO addGameDLCToBasket(String username, Long idGame, Long idDlc) throws ModelNoFound {
        var basket = basketRepository.findBasketByUser_Username(username)
                .orElseThrow(() -> new ModelNoFound("basket no found"));
        var game = gameDLCReadCommandModel.execute(idGame);
        if (basket.getGameDLCS() == null) {
            basket.setGameDLCS(new HashSet<>());
        }
        basket.getGameDLCS().add(game);
        basketRepository.save(basket);
        return gameDLCMapper.INSTANCE.toDTO_fromModel(game);
    }

    @Override
    public void putAwayGameDLCFromBasket(String username, Long idGame, Long idDlc) throws ModelNoFound {
        var basket = basketRepository.findBasketByUser_Username(username)
                .orElseThrow(() -> new ModelNoFound("basket no found"));
        var game = gameDLCReadCommandModel.execute(idGame);
        basket.getGameDLCS().remove(game);
        basketRepository.save(basket);
    }

    @Override
    public void clearAllBasket(String username) throws ModelNoFound {
        var basket = basketRepository.findBasketByUser_Username(username)
                .orElseThrow(() -> new ModelNoFound("basket no found"));
        basket.setGameDLCS(new HashSet<>());
        basket.setGameDLCS(new HashSet<>());
        basketRepository.save(basket);
    }

    @Override
    public BasketDTO readOneBasketByUsername(String username) throws ModelNoFound {
        var basket = basketRepository.findBasketByUser_Username(username)
                .orElseThrow(() -> new ModelNoFound("basket no found"));
        return BasketDTO
                .builder()
                .gameDLCDTOS(gameDLCMapper.INSTANCE
                        .toDTO_List_fromModel_List(new ArrayList<>(basket.getGameDLCS())))
                .gameDTOS(gameMapper.INSTANCE
                        .toDTO_List_fromModel_List(new ArrayList<>(basket.getGames())))
                .build();
    }


}
