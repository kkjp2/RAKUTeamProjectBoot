package rakuproject.raku.domain.board.dto.request;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import rakuproject.raku.domain.board.entity.BoardEntity;
import rakuproject.raku.domain.board.entity.CommentEntity;
import rakuproject.raku.domain.member.entity.MemberEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentRequestDTO {
    private Long comment_id;
    private String image;
    private String commentText;
    private String siren;
    private MemberEntity member;
    private BoardEntity board;

    public CommentEntity toEntity()
    {
        CommentEntity comments=
                CommentEntity.builder().
                        commId(comment_id).
                        image(image).
                        commentText(commentText).
                        siren(siren).
                        member(member).
                        board(board).
                        build();
        return comments;
    }


}