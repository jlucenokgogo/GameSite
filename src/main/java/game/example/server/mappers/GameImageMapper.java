package game.example.server.mappers;

import game.example.server.dto.GameImageDTO;
import game.example.server.model.GameImage;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface GameImageMapper {

    GameImageMapper INSTANCE = Mappers.getMapper(GameImageMapper.class);

    GameImageDTO toDTO_fromModel(GameImage gameImage);

    GameImage fromModel_toDTO(GameImageDTO gameImageDTO);
    ArrayList<GameImageDTO> toDTO_List_fromModel_List(ArrayList<GameImage> gameImages);

    ArrayList<GameImage> toModel_List_fromDTO_List(ArrayList<GameImageDTO> gameImages);
}
