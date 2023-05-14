package game.example.server.service.impl;

import game.example.server.dto.GameImageDTO;
import game.example.server.exceprion.ModelExist;
import game.example.server.exceprion.ModelNoFound;
import game.example.server.mappers.GameImageMapper;
import game.example.server.model.Game;
import game.example.server.model.GameImage;
import game.example.server.repositories.GameImageRepository;
import game.example.server.repositories.GameRepository;
import game.example.server.service.abstartion.GameImageService;
import game.example.server.utils.technical.commands.ReadCommandModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service("GameImageServiceImpl")
public class GameImageServiceImpl implements GameImageService {

    private final ReadCommandModel<GameRepository, Game> gameReadCommandModel;

    private final GameImageRepository gameImageRepository;

    private final GameImageMapper gameImageMapper;

    @Override
    public GameImageDTO save(GameImageDTO dto) throws ModelNoFound, ModelExist {
        if (gameImageRepository.existsGameImageByUrl(dto.getUrl())) {
            throw new ModelExist("this image exist");
        }
        var game = gameReadCommandModel.execute(dto.getIdGame());
        var image = gameImageMapper.INSTANCE.fromModel_toDTO(dto);
        image.setGame(game);
        var saved = gameImageRepository.save(image);
        return gameImageMapper.INSTANCE.toDTO_fromModel(saved);
    }

    @Override
    public GameImageDTO update(GameImageDTO dto) throws ModelNoFound {
        var updateImage = gameImageRepository.findById(dto.getIdImage())
                .orElseThrow(() -> new ModelNoFound("this model not exist"));
        updateImage.setUrl(dto.getUrl());
        var saved = gameImageRepository.save(updateImage);
        return gameImageMapper.INSTANCE.toDTO_fromModel(saved);
    }

    @Override
    public void delete(Long id) throws ModelNoFound {
        if (!gameImageRepository.existsGameImageByIdImage(id)) {
            throw new ModelNoFound("this image not exist");
        }
        gameImageRepository.deleteById(id);
    }

    @Override
    public GameImageDTO readOneModel(Long id) throws ModelNoFound {
        var gameImage = gameImageRepository.findById(id)
                .orElseThrow(() -> new ModelNoFound("this model not exist"));
        return gameImageMapper.INSTANCE.toDTO_fromModel(gameImage);
    }

    @Override
    public List<GameImageDTO> updateListImage(ArrayList<GameImageDTO> gameImageDTOS, Long id)
            throws ModelNoFound {
        var game = gameReadCommandModel.execute(id);
        var list = gameImageMapper.INSTANCE.toModel_List_fromDTO_List(gameImageDTOS)
                .stream().peek(gameImage -> gameImage.setGame(game)).collect(Collectors
                        .toCollection(ArrayList::new));
        var res = gameImageRepository.saveAllAndFlush(list);
        return gameImageMapper.INSTANCE.toDTO_List_fromModel_List((ArrayList<GameImage>) res);
    }
}
