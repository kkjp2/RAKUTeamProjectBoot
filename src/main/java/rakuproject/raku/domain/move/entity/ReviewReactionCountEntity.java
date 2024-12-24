package rakuproject.raku.domain.move.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import rakuproject.raku.domain.member.entity.MemberEntity;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "review_reaction_counts")
public class ReviewReactionCountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "count_id")
    private Long countId;

    @OneToOne
    @JoinColumn(name = "review_id", referencedColumnName = "review_id", nullable = false)
    private MoveReviewEntity reviewId;

    @Column(name = "like_count", nullable = false)
    private Integer likeCount = 0; // 초기 설정 0

    @Column(name = "dislike_count", nullable = false)
    private Integer dislikeCount = 0; // 싫어요 초기설정 0

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now(); // 반응 시간 저장.

}


