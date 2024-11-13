package com.example.board_test.board.dto.response;


import com.example.board_test.board.entity.BoardEntity;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class BoardResponseDTO {
    private final Long n_id;
    private final String content;
    private final int like;
    private final String title;
    private final int dislike;
    private final int view;
    private final String image;
    private final String member;
    private final String createdDate,modifiedDate;
    private final String siren;
    private final List<CommentResponseDTO> comments;


    public BoardResponseDTO(BoardEntity board)
    {
        this.n_id=board.getN_id();
        this.content=board.getContent();
        this.title=board.getTitle();
        this.like=board.getLike();
        this.dislike=board.getDislike();
        this.member=board.getMember().getNick();
        this.view=board.getView();
        this.image=board.getImage();
        this.createdDate=board.getCreatedDate();
        this.modifiedDate=board.getModifiedDate();
        this.siren=board.getSiren();
        this.comments = board.getCommentList().stream().map(CommentResponseDTO::new).collect(Collectors.toList());
    }




}
