package rakuproject.raku.domain.member.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rakuproject.raku.domain.member.dto.MemberDTO;
import rakuproject.raku.domain.member.entity.enums.MemberRole;
import rakuproject.raku.domain.member.service.CheckUserService;
import rakuproject.raku.domain.member.service.EmailCheckService;
import rakuproject.raku.domain.member.service.MemberService;
import rakuproject.raku.domain.mypage.service.UserServiceImpl;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    private final EmailCheckService emailCheckService;

    private final UserServiceImpl userService;

    private final CheckUserService checkUserService;

    //회원 가입 엔드포인트
    @PostMapping("/membership")
    public ResponseEntity<String> registerMember(@RequestBody MemberDTO memberDTO) {
        Long memberId = memberService.registerMember(memberDTO);
        return ResponseEntity.ok("Member registered with ID: " + memberId);
    }

    @GetMapping("/checkemail")
    public ResponseEntity<String> checkEmail(@RequestParam String id) {
        boolean isDuplicate = emailCheckService.isEmailDuplicate(id);
        if (isDuplicate) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email is already in use.");
        }
        return ResponseEntity.ok("Email is available.");

    }

    @GetMapping("/checkuser")
    public ResponseEntity<MemberRole> checkUserRole(@RequestHeader("Authorization") String authorizationHeader) {
        // Bearer 제거
        String token = authorizationHeader.substring(7);

        // 역할 확인
        Long userkey = userService.getUserKeyFromToken(token);

        MemberRole role = checkUserService.getUserRole(userkey);

        return ResponseEntity.ok(role); // 역할을 반환 (ROLE_USER 또는 ROLE_ADMIN)
    }

}
