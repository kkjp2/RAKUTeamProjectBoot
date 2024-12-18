package rakuproject.raku.domain.mypage.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rakuproject.raku.domain.member.entity.MemberEntity;
import rakuproject.raku.domain.mypage.repository.MypageRepository;
import rakuproject.raku.domain.mypage.repository.NickEditRepository;

@Service
@RequiredArgsConstructor
public class NickEditServiceImpl {

    @Autowired
    public final NickEditRepository nickEditRepository;

    public void updateNickname(Long userKey, String newNick) {
        MemberEntity user = nickEditRepository.findById(userKey)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userKey));

        user.setNick(newNick);
        nickEditRepository.save(user); // 변경된 닉네임 저장
    }





}
