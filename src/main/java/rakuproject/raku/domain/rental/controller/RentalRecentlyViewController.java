package rakuproject.raku.domain.rental.controller;



import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rakuproject.raku.domain.rental.entity.RentalRecentlyViewEntity;
import rakuproject.raku.domain.rental.service.RentalRecentlyViewService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/rental/recently")
public class RentalRecentlyViewController {

    private final RentalRecentlyViewService recentlyViewService;
    @GetMapping("/{userKey}")
    public ResponseEntity<?> getRecentlyViewed(@PathVariable Long userKey) {
        try {
            List<RentalRecentlyViewEntity> recentlyViewed = recentlyViewService.getRentalRecentlyViewd(userKey);
            return ResponseEntity.ok(recentlyViewed);
        } catch (IllegalArgumentException e) {
            log.warn("잘못된 요청: {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            log.error("서버 오류 발생: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().body("서버 내부 오류가 발생했습니다.");
        }
    }
}
