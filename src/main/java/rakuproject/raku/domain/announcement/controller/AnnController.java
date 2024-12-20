package rakuproject.raku.domain.announcement.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rakuproject.raku.domain.announcement.dto.AnnDTO;
import rakuproject.raku.domain.announcement.dto.response.AnnViewResponse;
import rakuproject.raku.domain.announcement.service.AnnService;

import java.io.IOException;

@RestController
@RequestMapping("/api/ann")
@RequiredArgsConstructor
public class AnnController {

    private final AnnService annService;


    @PostMapping("/membership")
    public ResponseEntity<String> createAnn(@ModelAttribute AnnDTO annDTO) {
        try {
            // Service 호출
            Long annkey = annService.createAnn(annDTO);
            return ResponseEntity.ok("Company saved successfully: " + annkey);
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Failed to save company: " + e.getMessage());
        }
    }

    @GetMapping("/view")
    public AnnViewResponse viewAnn(){
        return annService.viewAnn();
    }


}
