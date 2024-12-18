package rakuproject.raku.domain.board.entity;



import jakarta.persistence.*;
import lombok.*;
import rakuproject.raku.domain.member.entity.MemberEntity;

@Entity
@Table(name = "notice_festival_comment")
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public class FestivalCommentEntity extends TimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fcId;
    @Column(length = 255)
    private String image;
    @Column(columnDefinition = "Text")
    private String comment;
    @Column(columnDefinition = "Text")
    private String siren;

    @ManyToOne
    @JoinColumn(name = "fbId")
    private FestivalBoardEntity festivalBoard;

    @ManyToOne
    @JoinColumn(name = "userKey")
    private MemberEntity member;

    public void update(String comment, String image)
    {
        this.comment=comment;
        this.image=image;
    }
}
