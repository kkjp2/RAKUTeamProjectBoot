package rakuproject.raku.domain.company.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rakuproject.raku.domain.company.repository.CompanyFindRepository;
import rakuproject.raku.domain.member.entity.CompanyMemberEntity;

@Service
@RequiredArgsConstructor
public class CompanyFindServiceImpl {

    private final CompanyFindRepository companyFindRepository;

    public long findCompanyKey(long realtyMemberKey){

        CompanyMemberEntity companyMemberEntity = companyFindRepository.findByRealtyMemberKey(realtyMemberKey)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        return companyMemberEntity.getRealtyCompanyKey().getRealtyCompanyKey();

    }
}
