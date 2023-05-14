package game.example.server.rest;

import game.example.server.dto.GameDLCDTO;
import game.example.server.exceprion.ModelExist;
import game.example.server.exceprion.ModelNoFound;
import game.example.server.service.abstartion.GameDLCService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

@Slf4j
@RequestMapping("/api/gameDLCs")
@RestController
@RequiredArgsConstructor
@CrossOrigin
public class GameDLCRestController {

    private final GameDLCService gameDLCService;

    @GetMapping(value = "/getAllGameDLCs")
    @Async("controllerThreadPool")
    @ResponseBody
    public CompletableFuture<ArrayList<GameDLCDTO>> getAllGameDLCs() {
        return gameDLCService.getAllDto();
    }

    @GetMapping(value = "/getGameDLCById/{id}")
    public ResponseEntity<GameDLCDTO> getGameDLCById(@PathVariable Long id)
            throws ModelNoFound {
        return new ResponseEntity<>(gameDLCService.readOneModel(id), HttpStatus.OK);
    }

    // @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @PostMapping(value = "/saveNewGameDLC")
    public ResponseEntity<GameDLCDTO> saveNewGameDLC(@RequestBody GameDLCDTO gameDLCDTO)
            throws ModelNoFound, ModelExist {
        return new ResponseEntity<>(gameDLCService.save(gameDLCDTO), HttpStatus.CREATED);
    }

    // @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @PutMapping(value = "/updateGameDLC")
    public ResponseEntity<GameDLCDTO> updateGameDLC(@RequestBody GameDLCDTO gameDLCDTO)
            throws ModelNoFound {
        return new ResponseEntity<>(gameDLCService.update(gameDLCDTO), HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteGameDLCById/{id}")
    public ResponseEntity<HttpStatus> deleteGameDLCById(@PathVariable Long id)
            throws ModelNoFound {
        gameDLCService.delete(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
