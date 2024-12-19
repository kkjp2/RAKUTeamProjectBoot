package rakuproject.raku.domain.member.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rakuproject.raku.domain.member.dto.response.CheckUserResposne;
import rakuproject.raku.domain.member.entity.MemberEntity;
import rakuproject.raku.domain.member.entity.enums.MemberRole;
import rakuproject.raku.domain.member.repository.CheckUserRepository;
import rakuproject.raku.domain.mypage.dto.response.MypageMainResponse;

@Service
@RequiredArgsConstructor
public class CheckUserService {

    private final CheckUserRepository checkUserRepository;

    public MemberRole getUserRole(Long userkey) {
        MemberEntity user = checkUserRepository.findByUserKey(userkey)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        return user.getRole();
    }
}
