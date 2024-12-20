package rakuproject.raku.domain.board.dto.response;


import lombok.Getter;
import rakuproject.raku.domain.board.entity.ImageEntity;

@Getter
public class ImageResponseDTO {
    private final Long imgId;
    private final String image;
    private final Long userId;
    private final Long boardId;
    private final Long festivalBoardId;

    public ImageResponseDTO(ImageEntity image)
    {
        this.imgId=image.getImgId();
        this.image=image.getImage();
        this.userId=image.getMember().getUserKey();
        this.boardId=image.getBoard().getNId();
        this.festivalBoardId=image.getFestivalBoard().getFbId();
    }



}
