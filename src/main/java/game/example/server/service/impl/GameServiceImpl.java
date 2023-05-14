package game.example.server.service.impl;

import game.example.server.dto.GameDTO;
import game.example.server.dto.GameImageDTO;
import game.example.server.exceprion.ModelExist;
import game.example.server.exceprion.ModelNoFound;
import game.example.server.mappers.GameImageMapper;
import game.example.server.mappers.GameInfoMapper;
import game.example.server.mappers.GameMapper;
import game.example.server.model.Game;
import game.example.server.model.GameImage;
import game.example.server.model.Key;
import game.example.server.repositories.BasketRepository;
import game.example.server.repositories.GameRepository;
import game.example.server.repositories.KeyRepository;
import game.example.server.service.abstartion.GameImageService;
import game.example.server.service.abstartion.GameService;
import game.example.server.utils.technical.commands.ReadCommandModel;
import game.example.server.utils.technical.iterators.LoadingIterator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Slf4j
@Service("GameServiceImpl")
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {

    private final ReadCommandModel<GameRepository, Game> gameReadCommandModel;

    private final BasketRepository basketRepository;

    private final GameRepository gameRepository;

    private final GameMapper gameMapper;

    private final GameImageService gameImageService;

    private final GameInfoMapper gameInfoMapper;

    private final GameImageMapper gameImageMapper;

    private final LoadingIterator<Game> gameLoadingIterator;

    @Override
    public GameDTO save(GameDTO dto) throws ModelExist {
        if (gameRepository.existsGameByGameName(dto.getGameName())) {
            throw new ModelExist("this game exist");
        }
        var gameModel = gameMapper.INSTANCE.fromModel_toDTO(dto);
        var res = gameRepository.save(gameModel);
        return gameMapper.INSTANCE.toDTO_fromModel(res);
    }

    @Override
    public GameDTO update(GameDTO dto) throws ModelNoFound {
        var updateGame = gameReadCommandModel.execute(dto.getIdGame());
        gameMapper.update(updateGame, dto);
        var gameInfo = updateGame.getGameInfo();
        gameInfoMapper.update(gameInfo, dto.getGameInfoDTO());
        updateGame.setGameInfo(gameInfo);
        var res = gameRepository.save(updateGame);
        var dtoRes = gameMapper.INSTANCE.toDTO_fromModel(res);
        dtoRes.setGameImageDTOS(gameImageMapper.INSTANCE
                .toDTO_List_fromModel_List(new ArrayList<>(updateGame.getGameImages())));
        if (dto.getGameImageDTOS() != null) {
            var listImage = gameImageService.updateListImage(dto.getGameImageDTOS(),
                    dto.getIdGame());
            dtoRes.setGameImageDTOS((ArrayList<GameImageDTO>) listImage);
        }
        return dtoRes;
    }

    @Override
    @Transactional
    public void delete(Long id) throws ModelNoFound {
        if (!gameRepository.existsGameByIdGame(id)) {
            throw new ModelNoFound("this game exist");
        }
        gameRepository.deleteById(id);
    }

    @Override
    public GameDTO readOneModel(Long id) throws ModelNoFound {
        var readGame = gameReadCommandModel.execute(id);
        var dto = gameMapper.toDTO_fromModel(readGame);
        dto.setGameInfoDTO(gameInfoMapper.toDTO_fromModel(readGame.getGameInfo()));
        dto.setGameImageDTOS(gameImageMapper.toDTO_List_fromModel_List(
                (ArrayList<GameImage>) readGame.getGameImages().stream().toList()));
        return dto;
    }


    @Async("serviceThreadPool")
    @Override
    public CompletableFuture<ArrayList<GameDTO>> getAllDto() {
        var listResult = new ArrayList<Game>();
        while (gameLoadingIterator.hasNext()) {
            var game = gameLoadingIterator.getNext();
            listResult.add(game);
        }
        var gameArrayList = (ArrayList<GameDTO>) listResult.stream().map(g -> {
            var res = gameMapper.toDTO_fromModel(g);
            res.setGameImageDTOS(gameImageMapper
                    .toDTO_List_fromModel_List(new ArrayList<>(g.getGameImages())));
            res.setGameInfoDTO(gameInfoMapper.toDTO_fromModel(g.getGameInfo()));
            return res;
        }).collect(Collectors.toCollection(ArrayList::new));
        gameLoadingIterator.reset();
        return CompletableFuture.completedFuture(gameArrayList);
    }

    @Override
    public HashMap<GameDTO, Boolean> readOneModelByExistToBasket(Long idGame, String username)
            throws ModelNoFound {
        var res = new HashMap<GameDTO, Boolean>();
        var readGame = gameReadCommandModel.execute(idGame);
        var dto = gameMapper.toDTO_fromModel(readGame);
        dto.setGameInfoDTO(gameInfoMapper.toDTO_fromModel(readGame.getGameInfo()));
        dto.setGameImageDTOS(gameImageMapper.toDTO_List_fromModel_List(
                (ArrayList<GameImage>) readGame.getGameImages().stream().toList()));
        var basket = basketRepository.findBasketByUser_Username(username)
                .orElseThrow(() -> new ModelNoFound("basket no found"));
        GameDTO gameDTOFind = basket.getGames().stream()
                .map(gameMapper.INSTANCE::toDTO_fromModel)
                .filter(game -> game.equals(dto)).findFirst().orElse(null);
        if (gameDTOFind == null) {
            res.put(dto, false);
            return res;
        }
        res.put(dto, true);
        return res;
    }
}
