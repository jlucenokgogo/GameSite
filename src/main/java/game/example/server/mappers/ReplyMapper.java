package game.example.server.mappers;

import game.example.server.dto.ReplyDTO;
import game.example.server.model.Reply;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReplyMapper {

    ReplyMapper INSTANCE = Mappers.getMapper(ReplyMapper.class);

    @Mapping(target = "idGame", source = "reply.game.idGame")
    ReplyDTO toDTO_fromModel(Reply reply);

    Reply fromModel_toDTO(ReplyDTO replyDTO);

    ArrayList<ReplyDTO> toDTO_List_fromModel_List(ArrayList<Reply> replies);
}
