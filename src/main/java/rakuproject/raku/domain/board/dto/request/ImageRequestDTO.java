package rakuproject.raku.domain.board.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageRequestDTO {
    private Long img_id;
    private String image;
    private Long boardId;
    private Long festivalBoardId;
    private Long memberId;



}
