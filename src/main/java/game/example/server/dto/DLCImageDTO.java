package game.example.server.dto;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DLCImageDTO {

    private Long idImageDLC;

    private String url;

    private Long idGameDLC;
}
