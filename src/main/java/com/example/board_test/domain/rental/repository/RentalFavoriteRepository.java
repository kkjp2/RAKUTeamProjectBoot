package com.example.board_test.domain.rental.repository;

import com.example.board_test.domain.member.entity.MemberEntity;
import com.example.board_test.domain.rental.entity.RentalBoardEntity;
import com.example.board_test.domain.rental.entity.RentalFavoriteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RentalFavoriteRepository extends JpaRepository<RentalFavoriteEntity, Long> {
    boolean existsByMemberAndRentalBoard(MemberEntity member, RentalBoardEntity rentalBoard);

    List<RentalFavoriteEntity> findByMember(MemberEntity member);

    Optional<RentalFavoriteEntity> findByMemberAndRentalBoard(MemberEntity member,RentalBoardEntity rentalBoard);


}
