package game.example.server.rest;

import game.example.server.dto.GameDTO;
import game.example.server.dto.GameInfoDTO;
import game.example.server.exceprion.ModelExist;
import game.example.server.exceprion.ModelNoFound;
import game.example.server.service.abstartion.GameInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/api/gameInfos")
@RestController
@RequiredArgsConstructor
@CrossOrigin
public class GameInfoRestController {

    private final GameInfoService gameInfoService;


    @GetMapping(value = "/getGameInfoById/{id}")
    public ResponseEntity<GameInfoDTO> getGameInfoById(@PathVariable Long id)
            throws ModelNoFound {
        return new ResponseEntity<>(gameInfoService.readOneModel(id), HttpStatus.OK);
    }

    // @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @PostMapping(value = "/saveNewGameInfo")
    public ResponseEntity<GameInfoDTO> saveNewGameInfo(@RequestBody GameInfoDTO gameInfoDTO)
            throws ModelNoFound, ModelExist {
        return new ResponseEntity<>(gameInfoService.save(gameInfoDTO), HttpStatus.CREATED);
    }

    // @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @PutMapping(value = "/updateGameInfo")
    public ResponseEntity<GameInfoDTO> updateGameInfoInfo(@RequestBody GameInfoDTO gameInfoDTO)
            throws ModelNoFound {
        return new ResponseEntity<>(gameInfoService.update(gameInfoDTO), HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteGameInfoById/{id}")
    public ResponseEntity<HttpStatus> deleteGameInfoById(@PathVariable Long id)
            throws ModelNoFound {
        gameInfoService.delete(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
