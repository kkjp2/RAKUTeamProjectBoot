package rakuproject.raku.domain.board.dto.response;


import lombok.Getter;
import rakuproject.raku.domain.board.entity.FestivalBoardEntity;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class FestivalBoardResponseDTO {
    private final Long f_id;
    private final String title;
    private final String image;
    private final String content;
    private final int like;
    private final int view;
    private final int category;
    private final List<FestivalCommentResponseDTO> festivalComments;



    public FestivalBoardResponseDTO(FestivalBoardEntity festivalBoard)
    {
        this.f_id=festivalBoard.getFbId();
        this.title=festivalBoard.getTitle();
        this.image=festivalBoard.getImage();
        this.content= festivalBoard.getContent();
        this.like= festivalBoard.getLikeCnt();
        this.view= festivalBoard.getViewCnt();
        this.category= festivalBoard.getCategory();
        this.festivalComments=festivalBoard.getFestivalComments().stream().map(FestivalCommentResponseDTO::new).collect(Collectors.toList());
    }


}
