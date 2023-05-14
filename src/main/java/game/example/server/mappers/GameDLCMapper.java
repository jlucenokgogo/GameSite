package game.example.server.mappers;

import game.example.server.dto.GameDLCDTO;
import game.example.server.model.GameDLC;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface GameDLCMapper {

    GameDLCMapper INSTANCE = Mappers.getMapper(GameDLCMapper.class);

    GameDLCDTO toDTO_fromModel(GameDLC gameDLC);

    void update(@MappingTarget GameDLC update, GameDLCDTO dto);

    GameDLC fromModel_toDTO(GameDLCDTO gameDLCDTO);

    ArrayList<GameDLCDTO> toDTO_List_fromModel_List(ArrayList<GameDLC> gameDLCS);
}
