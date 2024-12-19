package rakuproject.raku.domain.board.entity;



import jakarta.persistence.*;
import lombok.*;
import rakuproject.raku.domain.member.entity.MemberEntity;

@Entity
@Table(name = "notice_favorite")
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public class FavoriteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long favId;
    @ManyToOne
    @JoinColumn(name = "userKey")
    private MemberEntity member;

    @ManyToOne
    @JoinColumn(name = "nId")
    private BoardEntity board;
    @ManyToOne
    @JoinColumn(name = "fbId")
    private FestivalBoardEntity festivalBoard;



}
