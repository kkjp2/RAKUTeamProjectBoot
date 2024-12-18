package com.example.board_test.domain.rental.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RentalReviewDTO {
    private Long reviewId;
    private int rating;              // 평점 (1~5)
    private String reviewContent;    // 리뷰 내용
    private LocalDateTime startDate; // 리뷰 기간 시작
    private LocalDateTime endDate;   // 리뷰 기간 종료
    private Long rentalBoardId;      // 렌탈 게시판 ID (리뷰가 속한 게시판)

}
