package game.example.server.service.abstartion;

import game.example.server.dto.DLCImageDTO;
import game.example.server.dto.GameImageDTO;
import game.example.server.exceprion.ModelNoFound;
import game.example.server.service.baseabstartion.CrudServiceDTO;

import java.util.ArrayList;
import java.util.List;

public interface DLCImageService extends CrudServiceDTO<DLCImageDTO> {

    List<DLCImageDTO> updateListImage(ArrayList<DLCImageDTO> dlcImageDTOS, Long id) throws ModelNoFound;
}
