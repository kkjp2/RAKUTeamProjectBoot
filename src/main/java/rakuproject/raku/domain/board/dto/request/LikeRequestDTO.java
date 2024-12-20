package rakuproject.raku.domain.board.dto.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class LikeRequestDTO {
    private Long boardId;  // 좋아요를 누를 게시글 ID
    private Long memberId; // 좋아요를 누르는 멤버 ID
}
