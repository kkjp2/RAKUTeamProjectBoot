package rakuproject.raku.domain.board.dto.response;



import lombok.Getter;
import lombok.Setter;
import rakuproject.raku.domain.board.entity.CommentEntity;

@Getter
@Setter
public class CommentResponseDTO {
    private final Long comment_id;
    private final String image;
    private final String commentText;
    private final String siren;
    private final String createdDate,modifiedDate;
    private final String nickname;
    private final Long board_id;

    public CommentResponseDTO(CommentEntity comment)
    {
        this.comment_id=comment.getCommId();
        this.image=comment.getImage();
        this.commentText=comment.getCommentText();
        this.siren=comment.getSiren();
        this.nickname=comment.getMember().getNick();
        this.board_id=comment.getBoard().getNId();
        this.createdDate=comment.getCreatedDate();
        this.modifiedDate=comment.getModifiedDate();
    }


}
