package rakuproject.raku.domain.company.controller;


import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rakuproject.raku.domain.board.dto.request.BoardRegisterRequest;
import rakuproject.raku.domain.company.dto.CompanyDTO;
import rakuproject.raku.domain.company.dto.response.CompanyInfoDTO;
import rakuproject.raku.domain.company.entity.CompanyEntity;
import rakuproject.raku.domain.company.service.CompanyFindServiceImpl;
import rakuproject.raku.domain.company.service.CompanyInfoServiceImpl;
import rakuproject.raku.domain.company.service.CompanyServiceImpl;

import java.io.IOException;

@RestController
@RequestMapping("/api/realty/company")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyServiceImpl companyService;

    private final CompanyFindServiceImpl companyFindService;

    private final CompanyInfoServiceImpl companyInfoService;


    @PostMapping("/membership")
    public ResponseEntity<String> registerMember(@ModelAttribute CompanyDTO companyDTO) {
        try {
            // Service 호출
            Long companyKey = companyService.registerCompany(companyDTO);
            return ResponseEntity.ok("Company saved successfully: " + companyKey);
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Failed to save company: " + e.getMessage());
        }
    }

    @GetMapping("/info")
    public CompanyEntity getCompanyInfo(@RequestParam long realtyMemberKey) {

        long realtyCompanyKey = companyFindService.findCompanyKey(realtyMemberKey);

        return companyInfoService.viewCompanyInfo(realtyCompanyKey);


    }

}
