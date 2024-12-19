package rakuproject.raku.domain.move.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ReactionCountResponse {
    private long likeCount;
    private long dislikeCount;
}
