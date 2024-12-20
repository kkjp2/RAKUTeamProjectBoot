package rakuproject.raku.domain.board.dto.response;



import lombok.Getter;
import lombok.NoArgsConstructor;
import rakuproject.raku.domain.board.entity.BoardEntity;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class BoardResponseDTO {
    private  Long n_id;
    private  String content;
    private  int like;
    private  String title;
    private  int dislike;
    private  int view;
    private  String image;
    private  String member;
    private  String createdDate,modifiedDate;
    private  List<CommentResponseDTO> comments;


    public BoardResponseDTO(BoardEntity board)
    {
        this.n_id=board.getNId();
        this.content=board.getContent();
        this.title=board.getTitle();
        this.like=board.getLikeCnt();
        this.dislike=board.getDislikeCnt();
        this.member=board.getMember().getNick();
        this.view=board.getViewCnt();
        this.image=board.getImage();
        this.createdDate=board.getCreatedDate();
        this.modifiedDate=board.getModifiedDate();
        this.comments = board.getCommentList().stream().map(CommentResponseDTO::new).collect(Collectors.toList());
    }




}
