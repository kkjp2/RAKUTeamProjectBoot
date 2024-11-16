package rakuproject.raku.domain.move.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReactionCountResponse {
    private long likeCount;
    private long dislikeCount;
    private int reactionValue;

    public ReactionCountResponse(long likeCount, long dislikeCount,int reactionValue) {
        this.likeCount = likeCount;
        this.dislikeCount = dislikeCount;
        this.reactionValue = reactionValue;
    }
}
