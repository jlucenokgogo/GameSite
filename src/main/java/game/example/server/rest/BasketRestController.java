package game.example.server.rest;

import game.example.server.dto.BasketDTO;
import game.example.server.dto.GameDLCDTO;
import game.example.server.dto.GameDTO;
import game.example.server.exceprion.ModelNoFound;
import game.example.server.service.abstartion.BasketService;
import game.example.server.service.abstartion.BasketStatistic;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Slf4j
@RequestMapping("/api/baskets")
@RestController
@RequiredArgsConstructor
@CrossOrigin
public class BasketRestController {

    private final BasketService basketService;

    private final BasketStatistic basketStatistic;

    // @PreAuthorize(value = "hasRole('ROLE_USER')")
    @PostMapping(value = "/addGameToBasket/{id}/{username}")
    public ResponseEntity<GameDTO> addGameToBasket(@PathVariable Long id,
                                                   @PathVariable String username)
            throws ModelNoFound {
        return new ResponseEntity<>(basketService.addGameToBasket(username,id), HttpStatus.OK);
    }

    // @PreAuthorize(value = "hasRole('ROLE_USER')")
    @PutMapping(value = "/putAwayGameFromBasket/{id}/{username}")
    public ResponseEntity<HttpStatus> putAwayGameFromBasket(@PathVariable Long id,
                                                      @PathVariable String username)
            throws ModelNoFound {
        basketService.putAwayGameFromBasket(username,id);
        return new ResponseEntity<>( HttpStatus.CREATED);
    }
    // @PreAuthorize(value = "hasRole('ROLE_USER')")
    @PostMapping(value = "/addGameDLCToBasket/{idGame}/{username}/{idDLC}")
    public ResponseEntity<GameDLCDTO> addGameDLCToBasket(@PathVariable Long idGame,
                                                             @PathVariable String username,
                                                             @PathVariable Long idDLC)
            throws ModelNoFound {
        return new ResponseEntity<>(basketService.addGameDLCToBasket(username,idGame,idDLC),
                HttpStatus.OK);
    }

    // @PreAuthorize(value = "hasRole('ROLE_USER')")
    @PutMapping(value = "/putAwayGameDLCFromBasket/{idGame}/{username}/{idDLC}")
    public ResponseEntity<HttpStatus> putAwayGameDLCFromBasket(@PathVariable Long idGame,
                                                            @PathVariable String username,
                                                            @PathVariable Long idDLC)
            throws ModelNoFound {
        basketService.putAwayGameDLCFromBasket(username,idGame,idDLC);
        return new ResponseEntity<>( HttpStatus.CREATED);
    }

    // @PreAuthorize(value = "hasRole('ROLE_USER')")
    @GetMapping(value = "/readOneBasketByUsername/{username}")
    public ResponseEntity<BasketDTO> readOneBasketByUsername(@PathVariable String username)
            throws ModelNoFound {
        return new ResponseEntity<>(basketService.readOneBasketByUsername(username), HttpStatus.OK);
    }

    // @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/allProfitByAllUser")
    public ResponseEntity<Double> allProfitByAllUser()
            throws ModelNoFound {
        return new ResponseEntity<>(basketStatistic.allProfitByAllUser(), HttpStatus.OK);
    }
    // @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/allProfitByOneGame")
    public ResponseEntity<HashMap<GameDTO,Double>> allProfitByOneGame()
            throws ModelNoFound {
        return new ResponseEntity<>(basketStatistic.allProfitByOneGame(), HttpStatus.OK);
    }

    // @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/allProfitByOneGameDLC")
    public ResponseEntity<HashMap<GameDLCDTO,Double>> allProfitByOneGameDLC()
            throws ModelNoFound {
        return new ResponseEntity<>(basketStatistic.allProfitByOneGameDLC(), HttpStatus.OK);
    }
}
