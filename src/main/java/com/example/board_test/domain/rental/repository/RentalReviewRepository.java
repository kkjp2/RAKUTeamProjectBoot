package com.example.board_test.domain.rental.repository;

import com.example.board_test.domain.rental.entity.RentalReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentalReviewRepository extends JpaRepository<RentalReviewEntity, Long> {

    List<RentalReviewEntity> findByRentalBoard_RbId(Long rbId);
}
