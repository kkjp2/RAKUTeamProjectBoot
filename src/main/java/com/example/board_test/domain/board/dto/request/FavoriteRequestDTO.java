package com.example.board_test.domain.board.dto.request;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class FavoriteRequestDTO {
    private Long fav_id;
    private Long memberId; // 사용자 ID
    private Long boardId; // 게시글 ID (선택적일 수 있음)
    private Long festivalBoardId; // 축제 게시글 ID (선택적일 수 있음)

//    public FavoriteEntity toEntity()
//    {
//        FavoriteEntity favorites=
//                FavoriteEntity.builder().
//                        favId(fav_id).
//                        member(memberId).
//                        board(boardId).
//                        festivalBoard(festivalBoardId).
//                        build();
//        return favorites;
//    }
}
