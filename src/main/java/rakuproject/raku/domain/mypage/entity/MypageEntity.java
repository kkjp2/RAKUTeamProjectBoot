package rakuproject.raku.domain.mypage.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
//@Table(name = "tbl_user")
@Getter
@Setter
public class MypageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userkey;

    @Column(nullable = false, unique = true)
    private String usernId;

    private String id;
    private String pwd;
    private String nick;
    private int address;
}
