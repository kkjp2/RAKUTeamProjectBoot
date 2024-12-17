package com.example.board_test.domain.rental.repository;

import com.example.board_test.domain.rental.entity.RentalReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalReservationRepository extends JpaRepository<RentalReservationEntity, Long> {

}
