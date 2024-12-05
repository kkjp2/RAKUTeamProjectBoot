package com.example.board_test.board.dto.request;


import com.example.board_test.board.entity.BoardEntity;
import com.example.board_test.board.entity.CommentEntity;
import com.example.board_test.domain.member.entity.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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