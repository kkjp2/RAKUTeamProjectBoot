package rakuproject.raku.domain.rental.entity;



import jakarta.persistence.*;
import lombok.*;
import rakuproject.raku.domain.member.entity.MemberEntity;

import java.time.LocalDateTime;

@Entity
@Table(name = "rental_reservation")
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class RentalReservationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resId;

    @ManyToOne
    @JoinColumn(name = "rbId")
    private RentalBoardEntity rentalBoard;

    @ManyToOne
    @JoinColumn(name = "userKey")
    private MemberEntity member;

    @Column(nullable = false)
    private LocalDateTime resDate;

    @Column(nullable = false)
    private LocalDateTime startDate;

    @Column(nullable = false)
    private LocalDateTime endDate;

    @Column(nullable = false)
    private int resPrice;

    @Column(nullable = false)
    private String resAddress;

    @Column(nullable = false)
    private String resHomeType;








}
