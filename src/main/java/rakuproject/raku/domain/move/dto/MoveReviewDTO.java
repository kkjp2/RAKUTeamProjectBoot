package rakuproject.raku.domain.move.dto;

import lombok.*;
import rakuproject.raku.domain.move.entity.enums.ReactionType;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MoveReviewDTO {
    private Long reviewId;
    private Long companyId;
    private Long userKey;
    private String comment;
    private Long rating;
    private String price;
    private String region;
    private LocalDate serviceDate;
    private LocalDateTime createdAt;

}

