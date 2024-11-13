package com.example.board_test.board.dto.response;

import com.example.board_test.board.entity.ImageEntity;
import lombok.Getter;

@Getter
public class ImageResponseDTO {
    private final Long imgId;
    private final String image;
    private final Long userId;
    private final Long boardId;
    private final Long festivalBoardId;

    public ImageResponseDTO(ImageEntity image)
    {
        this.imgId=image.getImg_id();
        this.image=image.getImage();
        this.userId=image.getMember().getUser_key();
        this.boardId=image.getBoard().getN_id();
        this.festivalBoardId=image.getFestivalBoard().getF_id();
    }



}
