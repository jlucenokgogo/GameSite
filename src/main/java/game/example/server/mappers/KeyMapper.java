package game.example.server.mappers;

import game.example.server.dto.KeyDTO;
import game.example.server.model.Key;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface KeyMapper {

    KeyMapper INSTANCE = Mappers.getMapper(KeyMapper.class);

    KeyDTO toDTO_fromModel(Key key);

    Key fromModel_toDTO(KeyDTO keyDTO);

    ArrayList<KeyDTO> toDTO_List_fromModel_List(ArrayList<Key> keys);

}
