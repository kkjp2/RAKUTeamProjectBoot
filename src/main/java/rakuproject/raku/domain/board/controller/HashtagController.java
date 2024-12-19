package rakuproject.raku.domain.board.controller;



import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import rakuproject.raku.domain.board.entity.HashTagEntity;
import rakuproject.raku.domain.board.service.HashTagService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/hashtag")
public class HashtagController {
    private final HashTagService hashTagService;

    @PostMapping("/search")
    public ResponseEntity<?> searchHashtag(@RequestBody String keyword)
    {
        hashTagService.searchTag(keyword);
        return ResponseEntity.ok("해시태그 검색 처리됨");
    }

    @GetMapping("/top10")
    public ResponseEntity<List<HashTagEntity>> getTop10Hashtags()
    {
        List<HashTagEntity> top10=hashTagService.getTop10HashTags();
        return ResponseEntity.ok(top10);
    }


}
