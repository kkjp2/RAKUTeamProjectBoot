package com.example.board_test.domain.board.dto.request;


import com.example.board_test.domain.board.entity.FestivalBoardEntity;
import com.example.board_test.domain.member.entity.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FestivalBoardRequestDTO {
    private Long f_id;
    private String title;
    private String image;
    private String content;
    private int like;
    private int view;
    private int category;
    private MemberEntity member;

    public FestivalBoardEntity toEntity()
    {
        FestivalBoardEntity festivals=
                FestivalBoardEntity.builder().
                        fbId(f_id).
                        title(title).
                        image(image).
                        likeCnt(0).
                        viewCnt(0).
                        category(category).
                        member(member).
                        build();
        return festivals;

    }



}
