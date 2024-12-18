package com.example.board_test.domain.rental.repository;

import com.example.board_test.domain.rental.entity.RentalChatMessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentalChatMessageRepository extends JpaRepository<RentalChatMessageEntity, Long> {
    List<RentalChatMessageEntity> findByChatRoom_RoomIdOrderByMsgSendTime(Long roomId);
}
