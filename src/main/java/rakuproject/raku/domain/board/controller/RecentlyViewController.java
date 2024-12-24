package rakuproject.raku.domain.board.controller;



import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rakuproject.raku.domain.board.entity.RecentlyViewEntity;
import rakuproject.raku.domain.board.service.RecentlyViewService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/recently")
public class RecentlyViewController {
    private final RecentlyViewService recentlyViewService;

//    @GetMapping
//    public ResponseEntity<List<RecentlyViewEntity>> getRecentlyViewdPosts()
//    {
//        try {
//            List<RecentlyViewEntity> recentlyViewedPosts = recentlyViewService.getRecentlyViewedPosts();
//            return ResponseEntity.ok(recentlyViewedPosts);
//        } catch (Exception e) {
//            log.error("최근 본 게시글 조회 중 오류 발생: {}", e.getMessage());
//            return ResponseEntity.badRequest().body(null);
//        }
//    }

    @GetMapping("/{userKey}")
    public ResponseEntity<?> getRecentlyViewed(@PathVariable Long userKey) {
        try {
            List<RecentlyViewEntity> recentlyViewed = recentlyViewService.getRecentlyViewed(userKey);
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
