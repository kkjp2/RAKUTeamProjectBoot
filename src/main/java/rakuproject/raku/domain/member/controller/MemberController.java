package rakuproject.raku.domain.member.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rakuproject.raku.domain.member.dto.MemberDTO;
import rakuproject.raku.domain.member.service.EmailCheckService;
import rakuproject.raku.domain.member.service.MemberService;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    private final EmailCheckService emailCheckService;

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
}
