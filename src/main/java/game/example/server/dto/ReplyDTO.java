package game.example.server.dto;

import lombok.*;

import java.io.Serializable;
import java.sql.Date;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReplyDTO implements Serializable {

    private Long idReply;

    private Date registration;

    private String desc;

    private String username;

    private Boolean activeReply;

    private Long idGame;
}
