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
    private Long companyId;               // 与 Entity 中的类型一致
    private Long userKey;                 // 修改为 Long，与 Entity 中的类型一致
    private String comment;
    private Long rating;
    private String price;
    private String region;
    private LocalDate serviceDate;
    private LocalDateTime createdAt;

    // 保留简化构造方法
    public MoveReviewDTO(Long reviewId, String userId, String comment) {
        this.reviewId = reviewId;
        this.userKey = Long.parseLong(userId); // 将 userId 转为 Long 类型
        this.comment = comment;
    }
}

