package rakuproject.raku.domain.company.entity;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import rakuproject.raku.domain.member.entity.enums.MemberRole;

import java.time.LocalDate;

@Entity
@Table(name = "realty_company")
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@ToString
@EntityListeners(value = {
        AuditingEntityListener.class
})
public class CompanyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long realtyCompanyKey;

    @Column(unique = true, nullable = false)
    private String license;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String img;

    @Column(nullable = false)
    private String number;

    @Column(nullable = false)
    private String zipcode;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String openhour;

    @Column(nullable = false)
    private String holiday;

    @Column(nullable = true)
    private String hompage;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String fax;

    @Column(nullable = false)
    private String map;

}
