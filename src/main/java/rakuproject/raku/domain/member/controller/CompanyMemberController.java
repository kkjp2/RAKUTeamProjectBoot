package rakuproject.raku.domain.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rakuproject.raku.domain.member.dto.CompanyMemberDTO;
import rakuproject.raku.domain.member.dto.MemberDTO;
import rakuproject.raku.domain.member.service.CompanyMemberService;

import java.io.IOException;

@RestController
@RequestMapping("/api/companyusers")
@RequiredArgsConstructor
public class CompanyMemberController {

    private final CompanyMemberService companyMemberService;


    @PostMapping("/membership")
    public ResponseEntity<String> registerMember(@ModelAttribute CompanyMemberDTO companyMemberDTO) {
        try {
            // Service 호출
            Long companyKey = companyMemberService.registerMember(companyMemberDTO);
            return ResponseEntity.ok("Company saved successfully: " + companyKey);
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Failed to save company: " + e.getMessage());
        }
    }


}
