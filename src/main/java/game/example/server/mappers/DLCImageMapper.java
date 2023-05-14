package game.example.server.mappers;

import game.example.server.dto.DLCImageDTO;
import game.example.server.dto.GameImageDTO;
import game.example.server.model.DLCImage;
import game.example.server.model.GameImage;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
        , unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DLCImageMapper {

    DLCImageMapper INSTANCE = Mappers.getMapper(DLCImageMapper.class);

    DLCImageDTO toDTO_fromModel(DLCImage gameDLC);

    DLCImage fromModel_toDTO(DLCImageDTO gameDLCDTO);

    ArrayList<DLCImageDTO> toDTO_List_fromModel_List(ArrayList<DLCImage> gameDLCS);

    ArrayList<DLCImage> toModel_List_fromDTO_List(ArrayList<DLCImageDTO> dlcImageDTOS);
}
