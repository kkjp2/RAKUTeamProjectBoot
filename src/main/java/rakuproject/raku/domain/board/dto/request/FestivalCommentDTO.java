package rakuproject.raku.domain.board.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FestivalCommentDTO {
    private Long fc_id;
    private String content;

}
