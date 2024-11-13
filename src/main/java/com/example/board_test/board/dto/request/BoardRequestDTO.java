package com.example.board_test.board.dto.request;


import com.example.board_test.board.entity.BoardEntity;
import com.example.board_test.domain.member.entity.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
    private MemberEntity member;
    private String siren;

    public BoardEntity toEntity()
    {
        BoardEntity boards=
                BoardEntity.builder().
                        n_id(n_id).
                        content(content).
                        title(title).
                        like(0).
                        dislike(0).
                        view(0).
                        image(image).
                        save(save).
                        category(category).
                        member(member).
                        siren(siren).

                        build();
        return boards;
    }





}
