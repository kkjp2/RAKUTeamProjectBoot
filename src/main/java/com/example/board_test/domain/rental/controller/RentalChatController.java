package com.example.board_test.domain.rental.controller;

import com.example.board_test.domain.member.entity.MemberEntity;
import com.example.board_test.domain.rental.entity.RentalChatMessageEntity;
import com.example.board_test.domain.rental.entity.RentalChatRoomEntity;
import com.example.board_test.domain.rental.service.RentalChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class RentalChatController {

    @Autowired
    private final RentalChatService rentalChatService;


    @PostMapping("/rooms")
    public ResponseEntity<RentalChatRoomEntity> createChatRoom
            (@RequestBody MemberEntity userA,
             @RequestBody MemberEntity userB)
    {
        return ResponseEntity.ok(rentalChatService.createChatRoom(userA,userB));
    }

    @GetMapping("/rooms/{roomId}")
    public ResponseEntity<RentalChatRoomEntity> getChatRoom(@PathVariable Long roomId)
    {
        return ResponseEntity.ok(rentalChatService.getChatRoom(roomId));
    }

    @PostMapping("/rooms/{roomId}/messages")
    public ResponseEntity<RentalChatMessageEntity> sendMessage
     (@PathVariable Long roomId,
      @RequestParam Long senderId,
      @RequestParam String content,
      @RequestParam RentalChatMessageEntity.MsgType msgType)
    {
        return ResponseEntity.ok(rentalChatService.sendMessage(roomId, senderId, content, msgType));
    }

    @GetMapping("/rooms/{roomId}/messages")
    public ResponseEntity<List<RentalChatMessageEntity>> getMessages(@PathVariable Long roomId)
    {
        return ResponseEntity.ok(rentalChatService.getMessages(roomId));
    }



}
