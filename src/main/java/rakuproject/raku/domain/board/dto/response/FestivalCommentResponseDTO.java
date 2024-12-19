package rakuproject.raku.domain.board.dto.response;



import lombok.Getter;
import rakuproject.raku.domain.board.entity.FestivalCommentEntity;

@Getter
public class FestivalCommentResponseDTO {
    private final Long fc_id;
    private final String image;
    private final String comment;
    private final String siren;
    private final String nickname;
    private final Long festival_id;
    private final String createdDate,modifiedDate;

    public FestivalCommentResponseDTO(FestivalCommentEntity festivalComment)
    {
        this.fc_id=festivalComment.getFcId();
        this.image=festivalComment.getImage();
        this.comment= festivalComment.getComment();
        this.siren= festivalComment.getSiren();
        this.nickname=festivalComment.getMember().getNick();
        this.festival_id=festivalComment.getFestivalBoard().getFbId();
        this.createdDate=festivalComment.getCreatedDate();
        this.modifiedDate=festivalComment.getModifiedDate();
    }



}
