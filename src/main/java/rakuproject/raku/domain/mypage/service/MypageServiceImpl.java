package rakuproject.raku.domain.mypage.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rakuproject.raku.domain.member.entity.MemberEntity;
import rakuproject.raku.domain.mypage.dto.response.MypageMainResponse;
import rakuproject.raku.domain.mypage.entity.MypageEntity;
import rakuproject.raku.domain.mypage.repository.MypageRepository;
import rakuproject.raku.domain.mypage.repository.UserRepository;
import rakuproject.raku.global.jwt.JwtUtil;

@Service
@RequiredArgsConstructor
public class MypageServiceImpl {

    @Autowired
    private MypageRepository mypageRepository;


    public MypageMainResponse getUserInfo(Long userkey) {
        MemberEntity user = mypageRepository.findByUserKey(userkey)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        return new MypageMainResponse(
                user.getId(),
                user.getPwd(),
                user.getNick(),
                user.getAddress()
        );
    }

}
