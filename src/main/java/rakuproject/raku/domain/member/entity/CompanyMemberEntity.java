package rakuproject.raku.domain.member.entity;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import rakuproject.raku.domain.company.entity.CompanyEntity;

@Entity
@Table(name = "realty_member")
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@ToString
@EntityListeners(value = {
        AuditingEntityListener.class
})
public class CompanyMemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long realtyMemberKey;

    @ManyToOne
    @JoinColumn(name = "realtyCompanyKey")
    private CompanyEntity realtyCompanyKey;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private int sex;

    @Column(nullable = false)
    private int age;

    @Column(nullable = false)
    private int experience;

    @Column(nullable = false, unique = true)
    private String id;

    @Column(nullable = false)
    private String pwd;

    @Column(nullable = false)
    private String img;



}
