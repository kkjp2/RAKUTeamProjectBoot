package rakuproject.raku.domain.board.dto.request;



import lombok.*;
import rakuproject.raku.domain.board.entity.BoardEntity;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class BoardRequestDTO {
    private Long n_id;
    private String content;
    private String title;
    private int like;
    private int dislike;
    private int view;
    private String image;
    private boolean save;
    private int category;
//    private MemberEntity member;
    private String siren;

    public BoardEntity toEntity()
    {
        BoardEntity boards=
                BoardEntity.builder().
                        nId(n_id).
                        content(content).
                        title(title).
                        likeCnt(0).
                        dislikeCnt(0).
                        viewCnt(0).
                        image(image).
                        save(save).
                        category(category).
//                        member(member).
                        siren(siren).

                        build();
        return boards;
    }





}
