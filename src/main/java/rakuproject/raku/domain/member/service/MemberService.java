package rakuproject.raku.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rakuproject.raku.domain.member.dto.MemberDTO;
import rakuproject.raku.domain.member.entity.MemberEntity;
import rakuproject.raku.domain.member.mapper.MemberMapper;
import rakuproject.raku.domain.member.repository.MemberRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepositroy;
    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;


    //회원 가입
    public Long registerMember(MemberDTO memberDTO){
        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(memberDTO.getPwd());
        memberDTO.setPwd(encodedPassword);

        MemberEntity memberEntity = MemberMapper.createEntity(memberDTO);

        // 회원 정보 저장
        memberRepositroy.save(memberEntity);
        return memberEntity.getUserKey();
    }

}
