package rakuproject.raku.domain.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rakuproject.raku.domain.company.entity.CompanyEntity;
import rakuproject.raku.domain.member.entity.MemberEntity;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<CompanyEntity, Long> {
}
