package rakuproject.raku.domain.company.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rakuproject.raku.domain.company.dto.response.CompanyInfoDTO;
import rakuproject.raku.domain.company.entity.CompanyEntity;
import rakuproject.raku.domain.company.repository.CompanyInfoRepository;

@Service
@RequiredArgsConstructor
public class CompanyInfoServiceImpl {

    private final CompanyInfoRepository companyInfoRepository;

    public CompanyEntity viewCompanyInfo(long realtyMemberKey) {

        return companyInfoRepository.findByRealtyCompanyKey(realtyMemberKey)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

    }


}
