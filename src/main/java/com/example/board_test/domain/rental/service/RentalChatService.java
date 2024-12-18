package com.example.board_test.domain.rental.service;

import com.example.board_test.domain.member.entity.MemberEntity;
import com.example.board_test.domain.rental.entity.RentalChatMessageEntity;
import com.example.board_test.domain.rental.entity.RentalChatRoomEntity;
import com.example.board_test.domain.rental.repository.RentalChatMessageRepository;
import com.example.board_test.domain.rental.repository.RentalChatRoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RentalChatService {

    private final RentalChatRoomRepository rentalChatRoomRepository;
    private final RentalChatMessageRepository rentalChatMessageRepository;

    @Transactional
    public RentalChatRoomEntity createChatRoom(MemberEntity userA, MemberEntity userB)
    {
        RentalChatRoomEntity chatRoom=RentalChatRoomEntity.builder().
                userA(userA).
                userB(userB).
                build();
        return rentalChatRoomRepository.save(chatRoom);
    }

    @Transactional(readOnly = true)
    public RentalChatRoomEntity getChatRoom(Long roomId)
    {
        return rentalChatRoomRepository.findById(roomId)
                .orElseThrow(()-> new IllegalArgumentException("존재하지 않는 "));
    }

    @Transactional
    public RentalChatMessageEntity sendMessage(Long roomId,Long senderId, String content, RentalChatMessageEntity.MsgType msgType)
    {
        RentalChatRoomEntity chatRoom=getChatRoom(roomId);

        MemberEntity sender= chatRoom.getUserA().getId().equals(senderId)
                ? chatRoom.getUserA()
                :chatRoom.getUserB();
        RentalChatMessageEntity message= RentalChatMessageEntity.builder().
                chatRoom(chatRoom).
                sender(sender).
                msgContent(content).
                msgSendTime(LocalDateTime.now()).
                build();
        return rentalChatMessageRepository.save(message);
    }

    @Transactional(readOnly = true)
    public List<RentalChatMessageEntity> getMessages(Long roomId)
    {
        return rentalChatMessageRepository.findByChatRoom_RoomIdOrderByMsgSendTime(roomId);
    }



}
