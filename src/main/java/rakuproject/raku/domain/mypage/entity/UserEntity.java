package rakuproject.raku.domain.mypage.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
//@Table(name = "tbl_user")
@Getter
@Setter
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userkey; // 회원 고유 번호 (userKey)

    @Column(nullable = false, unique = true)
    private String userId; // 사용자 ID


}
