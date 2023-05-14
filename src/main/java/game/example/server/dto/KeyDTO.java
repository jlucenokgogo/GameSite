package game.example.server.dto;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class KeyDTO  implements Serializable {

    private Long idKey;

    private String key;

    private Boolean active;
}
