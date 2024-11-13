package com.example.board_test.global.security;

import com.example.board_test.domain.member.dto.MemberDTO;
import com.example.board_test.domain.member.exception.NotFoundMemberException;
import com.example.board_test.domain.member.mapper.MemberMapper;
import com.example.board_test.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MemberDTO memberDTO=memberRepository.
                findById(username)
                .map(MemberMapper::createDTO)
                .orElseThrow(()-> NotFoundMemberException.EXCEPTION);
        return CustomUserDetails.create(memberDTO);
    }
}
