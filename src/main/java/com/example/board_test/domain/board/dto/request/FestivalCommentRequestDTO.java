package com.example.board_test.domain.board.dto.request;

import com.example.board_test.domain.board.entity.FestivalBoardEntity;
import com.example.board_test.domain.board.entity.FestivalCommentEntity;
import com.example.board_test.domain.member.entity.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
