package com.example.board_test.domain.rental.dto.response;


import com.example.board_test.domain.rental.entity.RentalReviewEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class RentalReviewResponseDTO {

    private Long reviewId;
    private int rating;
    private String reviewContent;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String nickname;
    private Long rbId;

    public RentalReviewResponseDTO(RentalReviewEntity rentalReview)
    {
        this.reviewId=rentalReview.getReviewId();
        this.rating=rentalReview.getRating();
        this.reviewContent=rentalReview.getReviewContent();
        this.startDate=rentalReview.getStartDate();
        this.endDate=rentalReview.getEndDate();
        this.nickname=rentalReview.getMember().getNick();
        this.rbId=rentalReview.getRentalBoard().getRbId();
    }
}
