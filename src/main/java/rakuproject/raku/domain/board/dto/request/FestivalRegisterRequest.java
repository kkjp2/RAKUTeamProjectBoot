package rakuproject.raku.domain.board.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FestivalRegisterRequest {
    private Long id;
    private String title;
    private String content;
    private String image;
    private int category;
}
