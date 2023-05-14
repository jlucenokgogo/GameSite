package game.example.server.mappers;

import game.example.server.dto.GameInfoDTO;
import game.example.server.model.GameInfo;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface GameInfoMapper {

    GameInfoMapper INSTANCE = Mappers.getMapper(GameInfoMapper.class);

    void update(@MappingTarget GameInfo update, GameInfoDTO dto);

    GameInfoDTO toDTO_fromModel(GameInfo gameInfo);

    GameInfo fromModel_toDTO(GameInfoDTO gameInfoDTO);

    ArrayList<GameInfoDTO> toDTO_List_fromModel_List(ArrayList<GameInfo> gameInfos);
}
