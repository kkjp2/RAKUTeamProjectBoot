package rakuproject.raku.domain.rental.entity;



import jakarta.persistence.*;
import lombok.*;
import rakuproject.raku.domain.member.entity.MemberEntity;

import java.time.LocalDateTime;

@Entity
@Table(name = "rental_pay")
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@ToString
public class RentalPayEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rPayId;

    @ManyToOne
    @JoinColumn(name = "rbId" ,nullable = false)
    private RentalBoardEntity rentalBoard;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userKey", nullable = false)
    private MemberEntity member;

    @Column(nullable = false)
    private String payType;

    @Column(nullable = false)
    private LocalDateTime payDate;

    @Column(nullable = false)
    private int pay;



}
