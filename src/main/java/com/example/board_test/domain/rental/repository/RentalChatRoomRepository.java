package com.example.board_test.domain.rental.repository;


import com.example.board_test.domain.rental.entity.RentalChatRoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalChatRoomRepository extends JpaRepository<RentalChatRoomEntity, Long> {

}
