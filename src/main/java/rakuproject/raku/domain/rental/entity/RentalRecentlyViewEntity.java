package rakuproject.raku.domain.rental.entity;



import jakarta.persistence.*;
import lombok.*;
import rakuproject.raku.domain.member.entity.MemberEntity;

@Entity
@Table(name = "rental_recently")
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@ToString
public class RentalRecentlyViewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rentalRId;

    @ManyToOne
    @JoinColumn(name = "userKey",nullable = false)
    private MemberEntity member;

    @ManyToOne
    @JoinColumn(name = "rbId",nullable = false)
    private RentalBoardEntity rentalBoard;


}
