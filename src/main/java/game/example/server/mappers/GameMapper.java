package game.example.server.mappers;

import game.example.server.dto.GameDTO;
import game.example.server.model.Game;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface GameMapper {

    GameMapper INSTANCE = Mappers.getMapper(GameMapper.class);

    void update(@MappingTarget Game update, GameDTO dto);

    GameDTO toDTO_fromModel(Game game);

    Game fromModel_toDTO(GameDTO gameDTO);

    ArrayList<GameDTO> toDTO_List_fromModel_List(ArrayList<Game> games);
}
