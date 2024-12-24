package rakuproject.raku.global.security;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import rakuproject.raku.domain.member.dto.MemberDTO;

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
