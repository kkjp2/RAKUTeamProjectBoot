package com.example.board_test.global.security;

import com.example.board_test.domain.member.dto.MemberDTO;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class MemberSecurity {
    public MemberDTO getMember()
    {
        return ((CustomUserDetails)SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal()).getMemberDTO();
    }
}
