package rakuproject.raku.domain.rental.dto.response;



import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import rakuproject.raku.domain.rental.entity.RentalReviewEntity;

import java.time.LocalDateTime;

@Getter
@Setter
@Data
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
