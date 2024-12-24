package rakuproject.raku.domain.mypage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rakuproject.raku.domain.member.entity.MemberEntity;
import rakuproject.raku.domain.mypage.entity.MypageEntity;
import rakuproject.raku.domain.mypage.entity.UserEntity;

import java.util.Optional;

public interface MypageRepository extends JpaRepository<MemberEntity, Long> {
    Optional<MemberEntity> findByUserKey(Long userkey); // 사용자 ID로 조회
}
