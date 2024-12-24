package rakuproject.raku.domain.move.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import rakuproject.raku.domain.member.entity.MemberEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@ToString
@Getter
@Setter
@AllArgsConstructor
@Entity
@Builder
@Table(name = "move_reviews")
public class MoveReviewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long reviewId;

    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "company_id")
    @JsonBackReference
    private MoveCompanyEntity company;

    @ManyToOne
    @JoinColumn(name = "user_key", referencedColumnName = "user_key")
    private MemberEntity userKey;

    @Column(name = "service_rating")
    private Long rating;

    @Column(name = "price")
    private String price;

    @Column(name = "region")
    private String region;//이사 지역...

    @Column(name = "service_date")
    private LocalDate serviceDate;

    @Column(name = "review_comment")
    private String comment;

    @Column(name = "review_created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

}

