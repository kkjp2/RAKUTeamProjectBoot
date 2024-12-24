package rakuproject.raku.domain.rental.entity;


import jakarta.persistence.*;
import lombok.*;
import rakuproject.raku.domain.board.entity.TimeEntity;
import rakuproject.raku.domain.member.entity.MemberEntity;

@Entity
@Table(name = "rental_image")
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@ToString
public class RentalImageEntity extends TimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rImgId;

    @Column(length = 255, nullable = false)
    private String rentalImage;

    @ManyToOne
    @JoinColumn(name = "userKey")
    private MemberEntity member;

    @ManyToOne
    @JoinColumn(name = "rbId")
    private RentalBoardEntity rentalBoard;


}
