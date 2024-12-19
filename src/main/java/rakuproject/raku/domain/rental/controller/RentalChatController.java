package rakuproject.raku.domain.rental.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rakuproject.raku.domain.member.entity.MemberEntity;
import rakuproject.raku.domain.rental.entity.RentalChatMessageEntity;
import rakuproject.raku.domain.rental.entity.RentalChatRoomEntity;
import rakuproject.raku.domain.rental.service.RentalChatService;

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
