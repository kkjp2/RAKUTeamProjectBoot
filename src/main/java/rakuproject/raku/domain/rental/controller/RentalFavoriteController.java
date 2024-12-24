package rakuproject.raku.domain.rental.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import rakuproject.raku.domain.rental.dto.request.RentalFavoriteRequestDTO;
import rakuproject.raku.domain.rental.service.RentalFavoriteService;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/rentalfavorite")
public class RentalFavoriteController {
    private final RentalFavoriteService rentalFavoriteService;


    @PostMapping
    public ResponseEntity<?> register(@RequestBody RentalFavoriteRequestDTO requestDTO)
    {
        try {
            log.info("요청 데이터: {}", requestDTO);
            rentalFavoriteService.addFavorite(requestDTO);
            return ResponseEntity.ok().body("즐겨찾기 등록 완료");
        } catch (IllegalArgumentException e) {
            log.warn("요청 오류: {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            log.error("서버 오류 발생: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().body("서버 내부 오류가 발생했습니다.");
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFavorite(@PathVariable("id") Long rfavId)
    {
        rentalFavoriteService.removeRentalFavorite(rfavId);
        return ResponseEntity.noContent().build();
    }

}
