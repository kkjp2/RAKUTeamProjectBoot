package rakuproject.raku.domain.rental.entity;



import jakarta.persistence.*;
import lombok.*;
import rakuproject.raku.domain.member.entity.MemberEntity;

@Entity
@Table(name = "rental_favorite")
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public class RentalFavoriteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rentalFavId;

    @ManyToOne
    @JoinColumn(name = "userKey")
    private MemberEntity member;

    @ManyToOne
    @JoinColumn(name = "rbId")
    private RentalBoardEntity rentalBoard;

}
