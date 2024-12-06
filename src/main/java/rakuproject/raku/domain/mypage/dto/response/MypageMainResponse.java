package rakuproject.raku.domain.mypage.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MypageMainResponse {
    private String id;
    private String pwd;
    private String nick;
    private int address;

    public MypageMainResponse(String id, String pwd, String nick, int address) {
        this.id = id;
        this.pwd = pwd;
        this.nick = nick;
        this.address = address;
    }

}
