package game.example.server.service.impl;

import game.example.server.dto.DLCImageDTO;
import game.example.server.exceprion.ModelExist;
import game.example.server.exceprion.ModelNoFound;
import game.example.server.mappers.DLCImageMapper;
import game.example.server.mappers.GameDLCMapper;
import game.example.server.model.DLCImage;
import game.example.server.model.Game;
import game.example.server.model.GameDLC;
import game.example.server.model.GameImage;
import game.example.server.repositories.DLCImageRepository;
import game.example.server.repositories.GameDLCRepository;
import game.example.server.repositories.GameRepository;
import game.example.server.service.abstartion.DLCImageService;
import game.example.server.utils.technical.commands.ReadCommandModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service("DLCImageServiceImpl")
@RequiredArgsConstructor
public class DLCImageServiceImpl implements DLCImageService {

    private final DLCImageRepository dlcImageRepository;

    private final DLCImageMapper dlcImageMapper;

    private final ReadCommandModel<GameDLCRepository, GameDLC> gameDLCReadCommandModel;

    @Override
    public DLCImageDTO save(DLCImageDTO dto) throws ModelNoFound, ModelExist {
        if (dlcImageRepository.existsDLCImageByUrl(dto.getUrl())) {
            throw new ModelExist("this image exist");
        }
        var game = gameDLCReadCommandModel.execute(dto.getIdGameDLC());
        var image = dlcImageMapper.INSTANCE.fromModel_toDTO(dto);
        image.setGameDLC(game);
        var saved = dlcImageRepository.save(image);
        return dlcImageMapper.INSTANCE.toDTO_fromModel(saved);
    }

    @Override
    public DLCImageDTO update(DLCImageDTO dto) throws ModelNoFound {
        var updateImage = dlcImageRepository.findById(dto.getIdImageDLC())
                .orElseThrow(() -> new ModelNoFound("this model not exist"));
        updateImage.setUrl(dto.getUrl());
        var saved = dlcImageRepository.save(updateImage);
        return dlcImageMapper.INSTANCE.toDTO_fromModel(saved);
    }

    @Override
    public void delete(Long id) throws ModelNoFound {
        if (!dlcImageRepository.existsDLCImageByIdImageDLC(id)) {
            throw new ModelNoFound("this image not exist");
        }
        dlcImageRepository.deleteById(id);
    }

    @Override
    public DLCImageDTO readOneModel(Long id) throws ModelNoFound {
        var gameImage = dlcImageRepository.findById(id)
                .orElseThrow(() -> new ModelNoFound("this model not exist"));
        return dlcImageMapper.INSTANCE.toDTO_fromModel(gameImage);
    }

    @Override
    public List<DLCImageDTO> updateListImage(ArrayList<DLCImageDTO> dlcImageDTOS, Long id) throws ModelNoFound {
        var game = gameDLCReadCommandModel.execute(id);
        var list = dlcImageMapper.INSTANCE.toModel_List_fromDTO_List(dlcImageDTOS)
                .stream().peek(gameImage -> gameImage.setGameDLC(game)).collect(Collectors
                        .toCollection(ArrayList::new));
        var res = dlcImageRepository.saveAllAndFlush(list);
        return dlcImageMapper.INSTANCE.toDTO_List_fromModel_List((ArrayList<DLCImage>) res);
    }
}
