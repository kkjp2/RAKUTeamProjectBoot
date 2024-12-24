package rakuproject.raku.domain.board.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import rakuproject.raku.domain.board.entity.FestivalBoardEntity;
import rakuproject.raku.domain.board.entity.FestivalCommentEntity;
import rakuproject.raku.domain.member.entity.MemberEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FestivalCommentRequestDTO {
    private Long fc_id;
    private String image;
    private String comment;
    private String siren;
    private MemberEntity member;
    private FestivalBoardEntity festivalBoard;

    public FestivalCommentEntity toEntity()
    {
        FestivalCommentEntity festivalComments=
                FestivalCommentEntity.builder().
                        fcId(fc_id).
                        image(image).
                        comment(comment).
                        siren(siren).
                        member(member).
                        festivalBoard(festivalBoard).
                        build();
        return festivalComments;
    }



}
