package rakuproject.raku.domain.board.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardRegisterRequest {
    private Long id;
    private String title;
    private String content;
    private String image;
    private int category;
    private List<ImageRequestDTO> images;

}
