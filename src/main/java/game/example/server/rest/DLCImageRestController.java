package game.example.server.rest;

import game.example.server.dto.DLCImageDTO;
import game.example.server.dto.GameDTO;
import game.example.server.exceprion.ModelExist;
import game.example.server.exceprion.ModelNoFound;
import game.example.server.service.abstartion.DLCImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/api/DLCImages")
@RestController
@RequiredArgsConstructor
@CrossOrigin
public class DLCImageRestController {

    private final DLCImageService dlcImageService;


    @GetMapping(value = "/getDLCImageDTOById/{id}")
    public ResponseEntity<DLCImageDTO> getDLCImageDTOById(@PathVariable Long id)
            throws ModelNoFound {
        return new ResponseEntity<>(dlcImageService.readOneModel(id), HttpStatus.OK);
    }

    // @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @PostMapping(value = "/saveNewDLCImage")
    public ResponseEntity<DLCImageDTO> saveNewDLCImage(@RequestBody DLCImageDTO dlcImageDTO)
            throws ModelNoFound, ModelExist {
        return new ResponseEntity<>(dlcImageService.save(dlcImageDTO), HttpStatus.CREATED);
    }

    // @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @PutMapping(value = "/updateDLCImage")
    public ResponseEntity<DLCImageDTO> updateDLCImage(@RequestBody DLCImageDTO dlcImageDTO)
            throws ModelNoFound {
        return new ResponseEntity<>(dlcImageService.update(dlcImageDTO), HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteDLCImageById/{id}")
    public ResponseEntity<HttpStatus> deleteDLCImageById(@PathVariable Long id)
            throws ModelNoFound {
        dlcImageService.delete(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
