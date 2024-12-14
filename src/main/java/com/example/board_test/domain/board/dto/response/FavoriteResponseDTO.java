package com.example.board_test.domain.board.dto.response;


import lombok.Getter;

@Getter
public class FavoriteResponseDTO {
    private Long favId; // 즐겨찾기 ID
    private Long memberId; // 사용자 ID
    private String memberNickname; // 사용자의 닉네임
    private Long boardId; // 게시글 ID
    private String boardTitle; // 게시글 제목
    private Long festivalBoardId; // 축제 게시글 ID
    private String festivalBoardTitle; // 축제 게시글 제목

    // 생성자
    public FavoriteResponseDTO(Long favId, Long memberId, String memberNickname, Long boardId, String boardTitle, Long festivalBoardId, String festivalBoardTitle) {
        this.favId = favId;
        this.memberId = memberId;
        this.memberNickname = memberNickname;
        this.boardId = boardId;
        this.boardTitle = boardTitle;
        this.festivalBoardId = festivalBoardId;
        this.festivalBoardTitle = festivalBoardTitle;
    }
}
