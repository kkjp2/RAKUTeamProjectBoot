package rakuproject.raku.domain.mypage.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rakuproject.raku.domain.mypage.dto.request.AddressEditRequest;
import rakuproject.raku.domain.mypage.dto.request.IdEditRequest;
import rakuproject.raku.domain.mypage.dto.request.NickEditRequest;
import rakuproject.raku.domain.mypage.dto.request.PwdEditRequest;
import rakuproject.raku.domain.mypage.dto.response.MypageMainResponse;
import rakuproject.raku.domain.mypage.service.*;

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

    @Autowired
    private PwdEditServiceImpl pwdEditService;


    @Autowired
    private IdEditServiceImpl idEditService;

    @Autowired
    private AddressEditService addressEditService;

    @Autowired
    private DeleteServiceImpl deleteService;

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

    @PostMapping("/pwdedit")
    public ResponseEntity<String> updatePwd(@RequestHeader("Authorization") String authorizationHeader , @RequestBody PwdEditRequest request) {
        String token = authorizationHeader.substring(7);

        Long userkey = userService.getUserKeyFromToken(token);
        try {
            pwdEditService.updatePwd(userkey, request.getPwd());
            return ResponseEntity.ok("Password updated successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to update password: " + e.getMessage());
        }
    }

    @PostMapping("/idedit")
    public ResponseEntity<String> updateId(@RequestHeader("Authorization") String authorizationHeader , @RequestBody IdEditRequest request) {
        String token = authorizationHeader.substring(7);

        Long userkey = userService.getUserKeyFromToken(token);
        try {
            idEditService.updateId(userkey, request.getId());
            return ResponseEntity.ok("Id updated successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to update id: " + e.getMessage());
        }
    }

    @PostMapping("/addressedit")
    public ResponseEntity<String> updateAddress(@RequestHeader("Authorization") String authorizationHeader , @RequestBody AddressEditRequest request) {
        String token = authorizationHeader.substring(7);

        Long userkey = userService.getUserKeyFromToken(token);
        try {
            addressEditService.updateId(userkey, request.getAddress());
            return ResponseEntity.ok("Address updated successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to update address: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteAccount(@RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.substring(7);

        Long userkey = userService.getUserKeyFromToken(token);

        deleteService.deleteUser(userkey);
        return ResponseEntity.ok("계정이 삭제되었습니다.");
    }
}
