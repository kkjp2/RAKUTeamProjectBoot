package rakuproject.raku.domain.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import rakuproject.raku.domain.auth.dto.request.AuthenticationRequest;
import rakuproject.raku.domain.auth.respository.CompanyAuthRepository;
import rakuproject.raku.domain.member.entity.CompanyMemberEntity;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CompanyAuthServiceImpl {

    private final CompanyAuthRepository companyAuthRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String companyAuth (AuthenticationRequest request) {

            CompanyMemberEntity  companyMember = companyAuthRepository.findById(request.getId()).orElseThrow(() -> new IllegalArgumentException("User not found"));;

            boolean matchPwd = passwordEncoder.matches(request.getPwd(), companyMember.getPwd());

            if(matchPwd) {
                return request.getId();
            } else{
                throw new IllegalArgumentException("Wrong password");
            }

    }
}
