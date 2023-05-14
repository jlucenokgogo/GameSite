package game.example.server.service.impl;

import game.example.server.dto.GameInfoDTO;
import game.example.server.exceprion.ModelExist;
import game.example.server.exceprion.ModelNoFound;
import game.example.server.mappers.GameInfoMapper;
import game.example.server.model.Game;
import game.example.server.repositories.GameInfoRepository;
import game.example.server.repositories.GameRepository;
import game.example.server.service.abstartion.GameInfoService;
import game.example.server.utils.technical.commands.ReadCommandModel;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("GameInfoServiceImpl")
@RequiredArgsConstructor
public class GameInfoServiceImpl implements GameInfoService {

    private final GameInfoRepository gameInfoRepository;

    private final GameInfoMapper gameInfoMapper;

    private final ReadCommandModel<GameRepository, Game> gameReadCommandModel;

    @Override
    public GameInfoDTO save(GameInfoDTO dto) throws ModelExist, ModelNoFound {
        if (gameInfoRepository.existsGameInfoByGame_IdGame(dto.getIdGame())) {
            throw new ModelExist("this model exist");
        }
        var game = gameReadCommandModel.execute(dto.getIdGame());
        var gameInfo = gameInfoMapper.INSTANCE.fromModel_toDTO(dto);
        gameInfo.setGame(game);
        var res = gameInfoRepository.save(gameInfo);
        return gameInfoMapper.INSTANCE.toDTO_fromModel(res);
    }

    @Override
    public GameInfoDTO update(@NotNull GameInfoDTO dto) throws ModelNoFound {
        if (!gameInfoRepository.existsGameInfoByGame_IdGame(dto.getIdGame())) {
            throw new ModelNoFound("this not model exist");
        }
        var gameInfo = gameInfoRepository.findById(dto.getIdGameInfo())
                .orElseThrow(() -> new ModelNoFound("this not model exist"));
        if (dto.getIdGame() != null) {
            var game = gameReadCommandModel.execute(dto.getIdGame());
            gameInfo.setGame(game);
        }
        gameInfoMapper.update(gameInfo, dto);
        var res = gameInfoRepository.save(gameInfo);
        return gameInfoMapper.INSTANCE.toDTO_fromModel(res);
    }

    @Override
    @Transactional
    public void delete(Long id) throws ModelNoFound {
        if (!gameInfoRepository.existsGameInfoByIdGameInfo(id)) {
            throw new ModelNoFound("this model exist");
        }
        gameInfoRepository.deleteById(id);
    }

    @Override
    public GameInfoDTO readOneModel(Long id) throws ModelNoFound {
        var gameInfo = gameInfoRepository.findById(id)
                .orElseThrow(() -> new ModelNoFound("this not model exist"));
        return gameInfoMapper.INSTANCE.toDTO_fromModel(gameInfo);
    }
}
