package com.example.board_test.domain.rental.repository;

import com.example.board_test.domain.rental.entity.RentalBoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface RentalBoardRepository extends JpaRepository<RentalBoardEntity, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE RentalBoardEntity rb SET rb.rentalViewCnt=rb.rentalViewCnt+1 WHERE rb.rbId=:rbId")
    void View(@Param("rbId")Long rbId);

    @Modifying
    @Transactional
    @Query("UPDATE RentalBoardEntity rb SET rb.rentalViewCnt=:view WHERE rb.rbId= :rbId")
    void updateRentalBoardStats(@Param("view") int view);
}
