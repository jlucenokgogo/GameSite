package game.example.server.service.impl;

import game.example.server.dto.DLCImageDTO;
import game.example.server.dto.GameDLCDTO;
import game.example.server.exceprion.ModelExist;
import game.example.server.exceprion.ModelNoFound;
import game.example.server.mappers.DLCImageMapper;
import game.example.server.mappers.GameDLCMapper;
import game.example.server.model.DLCImage;
import game.example.server.model.Game;
import game.example.server.model.GameDLC;
import game.example.server.repositories.GameDLCRepository;
import game.example.server.repositories.GameRepository;
import game.example.server.service.abstartion.DLCImageService;
import game.example.server.service.abstartion.GameDLCService;
import game.example.server.utils.technical.commands.ReadCommandModel;
import game.example.server.utils.technical.iterators.LoadingIterator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Slf4j
@Service("GameDLCServiceImpl")
@RequiredArgsConstructor
public class GameDLCServiceImpl implements GameDLCService {

    private final GameDLCRepository gameDLCRepository;

    private final GameDLCMapper gameDLCMapper;

    private final DLCImageMapper dlcImageMapper;

    private final DLCImageService dlcImageService;

    private final ReadCommandModel<GameRepository, Game> gameReadCommandModel;

    private final LoadingIterator<GameDLC> gameDLCLoadingIterator;

    @Override
    public GameDLCDTO save(GameDLCDTO dto) throws ModelExist, ModelNoFound {
        if (gameDLCRepository.existsGameDLCByDlsName(dto.getDlsName())) {
            throw new ModelExist("this game exist");
        }
        var game = gameReadCommandModel.execute(dto.getIdGame());
        var gameDlcModel = gameDLCMapper.INSTANCE.fromModel_toDTO(dto);
        gameDlcModel.setGame(game);
        var res = gameDLCRepository.save(gameDlcModel);
        return gameDLCMapper.INSTANCE.toDTO_fromModel(res);
    }

    @Override
    public GameDLCDTO update(GameDLCDTO dto) throws ModelNoFound {
        var updateGameDlc = gameDLCRepository.findById(dto.getIdGameDlc())
                .orElseThrow(() -> new ModelNoFound("this gameDlc not exist"));
        if (dto.getIdGame() != null) {
            var game = gameReadCommandModel.execute(dto.getIdGame());
            updateGameDlc.setGame(game);
        }
        gameDLCMapper.update(updateGameDlc, dto);
        var res = gameDLCRepository.save(updateGameDlc);
        var dtoRes = gameDLCMapper.INSTANCE.toDTO_fromModel(res);
        dtoRes.setGameDlcImages(dlcImageMapper.INSTANCE
                .toDTO_List_fromModel_List(new ArrayList<>(updateGameDlc.getGameDlcImages())));
        if (dto.getGameDlcImages() != null) {
            var updateImage = dlcImageService
                    .updateListImage(dto.getGameDlcImages(), dto.getIdGameDlc());
            dtoRes.setGameDlcImages((ArrayList<DLCImageDTO>) updateImage);
        }
        return dtoRes;
    }

    @Override
    @Transactional
    public void delete(Long id) throws ModelNoFound {
        if (!gameDLCRepository.existsGameDLCByIdGameDlc(id)) {
            throw new ModelNoFound("this gameDlc not exist");
        }
        gameDLCRepository.deleteById(id);
    }

    @Override
    public GameDLCDTO readOneModel(Long id) throws ModelNoFound {
        var gameDlc = gameDLCRepository.findById(id)
                .orElseThrow(() -> new ModelNoFound("this gameDlc not exist"));
        return gameDLCMapper.INSTANCE.toDTO_fromModel(gameDlc);
    }

    @Override
    public CompletableFuture<ArrayList<GameDLCDTO>> getAllDto() {
        var listResult = new ArrayList<GameDLC>();
        while (gameDLCLoadingIterator.hasNext()) {
            var game = gameDLCLoadingIterator.getNext();
            listResult.add(game);
        }
        return getArrayListCompletableFuture(listResult);
    }

    @Override
    public CompletableFuture<ArrayList<GameDLCDTO>> getAllDlcBYGameId(Long idGame) {
        var listResult = gameDLCRepository.findGameDLCSByGame_IdGame(idGame);
        return getArrayListCompletableFuture(listResult);
    }

    @NotNull
    private CompletableFuture<ArrayList<GameDLCDTO>> getArrayListCompletableFuture(@NotNull List<GameDLC> listResult) {
        var gameDlcArrayList = (ArrayList<GameDLCDTO>) listResult.stream().map(g -> {
            var res = gameDLCMapper.toDTO_fromModel(g);
            res.setGameDlcImages(dlcImageMapper
                    .toDTO_List_fromModel_List(new ArrayList<>(g.getGameDlcImages())));
            return res;
        }).collect(Collectors.toCollection(ArrayList::new));
        gameDLCLoadingIterator.reset();
        return CompletableFuture.completedFuture(gameDlcArrayList);
    }
}
