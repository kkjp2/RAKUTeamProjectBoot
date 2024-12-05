package com.example.board_test.board.dto.request;


import com.example.board_test.board.entity.BoardEntity;
import com.example.board_test.board.entity.FavoriteEntity;
import com.example.board_test.board.entity.FestivalBoardEntity;
import com.example.board_test.domain.member.entity.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FavoriteRequestDTO {
    private Long fav_id;
    private MemberEntity memberId; // 사용자 ID
    private BoardEntity boardId; // 게시글 ID (선택적일 수 있음)
    private FestivalBoardEntity festivalBoardId; // 축제 게시글 ID (선택적일 수 있음)

    public FavoriteEntity toEntity()
    {
        FavoriteEntity favorites=
                FavoriteEntity.builder().
                        favId(fav_id).
                        member(memberId).
                        board(boardId).
                        festivalBoard(festivalBoardId).
                        build();
        return favorites;
    }
}
