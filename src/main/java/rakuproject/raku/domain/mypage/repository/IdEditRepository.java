package rakuproject.raku.domain.mypage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rakuproject.raku.domain.member.entity.MemberEntity;

public interface IdEditRepository extends JpaRepository<MemberEntity, Long> {
}
