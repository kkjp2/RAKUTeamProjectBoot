package com.example.board_test.domain.member.service;

import com.example.board_test.domain.member.dto.MemberDTO;
import com.example.board_test.domain.member.entity.MemberEntity;
import com.example.board_test.domain.member.mapper.MemberMapper;
import com.example.board_test.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepositroy;
    private final PasswordEncoder passwordEncoder;


    //회원 가입
    public Long registerMember(MemberDTO memberDTO){
        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(memberDTO.getPwd());
        memberDTO.setPwd(encodedPassword);

        MemberEntity memberEntity = MemberMapper.createEntity(memberDTO);

        // 회원 정보 저장
        memberRepositroy.save(memberEntity);
        return memberEntity.getUser_key();
    }
}
