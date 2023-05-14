package game.example.server.rest;

import game.example.server.dto.GameImageDTO;
import game.example.server.exceprion.ModelExist;
import game.example.server.exceprion.ModelNoFound;
import game.example.server.service.abstartion.GameImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/api/gameImages")
@RestController
@RequiredArgsConstructor
@CrossOrigin
public class GameImageRestController {

    private final GameImageService gameImageService;


    @GetMapping(value = "/getGameImageById/{id}")
    public ResponseEntity<GameImageDTO> getGameImageById(@PathVariable Long id)
            throws ModelNoFound {
        return new ResponseEntity<>(gameImageService.readOneModel(id), HttpStatus.OK);
    }

    // @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @PostMapping(value = "/saveNewGameImage")
    public ResponseEntity<GameImageDTO> saveNewGameImage(@RequestBody GameImageDTO gameImageDTO)
            throws ModelNoFound, ModelExist {
        return new ResponseEntity<>(gameImageService.save(gameImageDTO), HttpStatus.CREATED);
    }

    // @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @PutMapping(value = "/updateGameImage")
    public ResponseEntity<GameImageDTO> updateGameImage(@RequestBody GameImageDTO gameImageDTO)
            throws ModelNoFound {
        return new ResponseEntity<>(gameImageService.update(gameImageDTO), HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteGameImageById/{id}")
    public ResponseEntity<HttpStatus> deleteGameImageById(@PathVariable Long id)
            throws ModelNoFound {
        gameImageService.delete(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
