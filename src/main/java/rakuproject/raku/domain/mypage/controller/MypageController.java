package rakuproject.raku.domain.mypage.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rakuproject.raku.domain.mypage.dto.request.NickEditRequest;
import rakuproject.raku.domain.mypage.dto.response.MypageMainResponse;
import rakuproject.raku.domain.mypage.service.MypageServiceImpl;
import rakuproject.raku.domain.mypage.service.NickEditServiceImpl;
import rakuproject.raku.domain.mypage.service.UserServiceImpl;

@RestController
@RequestMapping("/api/mypage")
@RequiredArgsConstructor
public class MypageController {

    @Autowired
    private MypageServiceImpl mypageService;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private NickEditServiceImpl nickEditService;


    @GetMapping
    public MypageMainResponse getUserInfo(@RequestHeader("Authorization") String authorizationHeader){

        String token = authorizationHeader.substring(7);

        Long userkey = userService.getUserKeyFromToken(token);

        return mypageService.getUserInfo(userkey);
    }

    @PostMapping("/nickedit")
    public ResponseEntity<String> updateNickname(@RequestHeader("Authorization") String authorizationHeader ,@RequestBody NickEditRequest request) {
        String token = authorizationHeader.substring(7);

        Long userkey = userService.getUserKeyFromToken(token);
        try {
            nickEditService.updateNickname(userkey, request.getNick());
            return ResponseEntity.ok("Nickname updated successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to update nickname: " + e.getMessage());
        }
    }
}
