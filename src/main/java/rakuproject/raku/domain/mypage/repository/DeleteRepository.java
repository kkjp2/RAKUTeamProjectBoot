package rakuproject.raku.domain.mypage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rakuproject.raku.domain.member.entity.MemberEntity;
import rakuproject.raku.domain.member.repository.MemberRepository;

import java.util.Optional;

@Repository
public interface DeleteRepository extends JpaRepository<MemberEntity, Long> {
    Optional<MemberEntity> findByUserKey(Long userkey);
}
