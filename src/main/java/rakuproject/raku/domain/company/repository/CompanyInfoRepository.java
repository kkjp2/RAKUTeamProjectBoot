package rakuproject.raku.domain.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rakuproject.raku.domain.company.entity.CompanyEntity;
import rakuproject.raku.domain.member.entity.CompanyMemberEntity;

import java.util.Optional;

public interface CompanyInfoRepository extends JpaRepository<CompanyEntity, Long> {
    Optional<CompanyEntity> findByRealtyCompanyKey(Long realtyCompanyKey);
}
