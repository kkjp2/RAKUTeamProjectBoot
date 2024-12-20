package rakuproject.raku.domain.announcement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rakuproject.raku.domain.announcement.entity.AnnEntity;
import rakuproject.raku.domain.member.entity.MemberEntity;

import java.util.Optional;

public interface AnnRepository extends JpaRepository<AnnEntity, Long> {
}
