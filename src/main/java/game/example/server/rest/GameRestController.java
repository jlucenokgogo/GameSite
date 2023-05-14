package game.example.server.rest;

import game.example.server.dto.GameDTO;
import game.example.server.dto.ReplyDTO;
import game.example.server.exceprion.ModelExist;
import game.example.server.exceprion.ModelNoFound;
import game.example.server.service.abstartion.GameService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.CompletableFuture;

@Slf4j
@RequestMapping("/api/games")
@RestController
@RequiredArgsConstructor
@CrossOrigin
public class GameRestController {

    private final GameService gameService;

    @GetMapping(value = "/getAllGames")
    @Async("controllerThreadPool")
    @ResponseBody
    public CompletableFuture<ArrayList<GameDTO>> getAllGames() {
        return gameService.getAllDto();
    }

    @GetMapping(value = "/getGameById/{id}")
    public ResponseEntity<GameDTO> getGameById(@PathVariable Long id)
            throws ModelNoFound {
        return new ResponseEntity<>(gameService.readOneModel(id), HttpStatus.OK);
    }

    // @PreAuthorize(value = "hasRole('ROLE_CLIENT')")
    @GetMapping(value = "/getGameById/{id}/{username}")
    public ResponseEntity<HashMap<GameDTO, Boolean>> getGameById(@PathVariable Long id,
                                                                 @PathVariable String username)
            throws ModelNoFound {
        return new ResponseEntity<>(gameService.readOneModelByExistToBasket(id, username), HttpStatus.OK);
    }

    // @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @PostMapping(value = "/saveNewGame")
    public ResponseEntity<GameDTO> saveNewGame(@RequestBody GameDTO gameDTO)
            throws ModelNoFound, ModelExist {
        return new ResponseEntity<>(gameService.save(gameDTO), HttpStatus.CREATED);
    }

    // @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @PutMapping(value = "/updateGame")
    public ResponseEntity<GameDTO> updateGame(@RequestBody GameDTO gameDTO)
            throws ModelNoFound {
        return new ResponseEntity<>(gameService.update(gameDTO), HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteGameById/{id}")
    public ResponseEntity<HttpStatus> deleteGameById(@PathVariable Long id)
            throws ModelNoFound {
        gameService.delete(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
