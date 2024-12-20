package rakuproject.raku.domain.rental.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rakuproject.raku.domain.member.entity.MemberEntity;
import rakuproject.raku.domain.rental.entity.RentalBoardEntity;
import rakuproject.raku.domain.rental.entity.RentalFavoriteEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface RentalFavoriteRepository extends JpaRepository<RentalFavoriteEntity, Long> {
    boolean existsByMemberAndRentalBoard(MemberEntity member, RentalBoardEntity rentalBoard);

    List<RentalFavoriteEntity> findByMember(MemberEntity member);

    Optional<RentalFavoriteEntity> findByMemberAndRentalBoard(MemberEntity member,RentalBoardEntity rentalBoard);


}
