package rakuproject.raku.domain.announcement.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import rakuproject.raku.domain.company.entity.CompanyEntity;
import rakuproject.raku.domain.member.entity.MemberEntity;
import rakuproject.raku.domain.member.entity.enums.MemberRole;

import java.time.LocalDate;

@Entity
@Table(name = "tblAnn")
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@ToString
@EntityListeners(value = {
        AuditingEntityListener.class
})
public class AnnEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "annkey")
    private Long annkey;

    @ManyToOne
    @JoinColumn(name = "user_key")
    private MemberEntity userkey;

    @Column( nullable = false)
    private String title;

    @Column(nullable = false)
    private String img;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String borndate;

}
