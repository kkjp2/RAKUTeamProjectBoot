package rakuproject.raku.domain.house.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import rakuproject.raku.domain.house.dto.HouseDetailDTO;
import rakuproject.raku.domain.house.entity.HouseDetail;
import rakuproject.raku.domain.house.service.HouseDetailService;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/houses")
public class HouseDetailController {

    private final HouseDetailService houseDetailService;

    public HouseDetailController(HouseDetailService houseDetailService) {
        this.houseDetailService = houseDetailService;
    }

    // 모든 집 상세 정보를 가져오는 메서드
    @GetMapping
    public ResponseEntity<List<HouseDetailDTO>> getAllHouseDetails() {
        List<HouseDetail> houseDetails = houseDetailService.getAllHouseDetails();
        List<HouseDetailDTO> houseDetailDtos = houseDetails.stream()
                .map(HouseDetailDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(houseDetailDtos);
    }

    // 특정 빌딩 번호로 집 상세 정보를 가져오는 메서드
    @GetMapping("/{bBuildNumber}")
    public ResponseEntity<HouseDetailDTO> getHouseDetail(@PathVariable Long bBuildNumber) {
        return houseDetailService.getHouseDetailByBBuildNumber(bBuildNumber)
                .map(houseDetail -> ResponseEntity.ok(new HouseDetailDTO(houseDetail)))
                .orElse(ResponseEntity.notFound().build());
    }

    // 집 상세 정보 생성 메서드
    @PostMapping
    public ResponseEntity<?> createHouseDetail(@RequestBody HouseDetail houseDetail) {
        // 필수 입력 항목인 주소 체크
        if (houseDetail.getAddress() == null || houseDetail.getAddress().isEmpty()) {
            return ResponseEntity.badRequest().body("주소는 필수 입력 항목입니다.");
        }

        try {
            // HouseDetail을 저장
            HouseDetail createdHouse = houseDetailService.createHouseDetail(houseDetail);

            // DTO 객체로 반환
            HouseDetailDTO houseDetailDTO = new HouseDetailDTO(createdHouse);
            return ResponseEntity.status(HttpStatus.CREATED).body(houseDetailDTO);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Failed to create HouseDetail: " + e.getMessage());
        }
    }

    // 집 상세 정보 업데이트 메서드
    @PutMapping("/{id}")
    public ResponseEntity<HouseDetailDTO> updateHouseDetail(
            @PathVariable Long id,
            @RequestBody HouseDetail updatedData) {
        return houseDetailService.updateHouseDetail(id, updatedData)
                .map(updatedHouse -> ResponseEntity.ok(new HouseDetailDTO(updatedHouse)))
                .orElse(ResponseEntity.notFound().build());
    }

    // 집 상세 정보 삭제 메서드
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHouseDetail(@PathVariable Long id) {
        houseDetailService.deleteHouseDetail(id);
        return ResponseEntity.noContent().build();
    }

    // 집 이미지 업로드 메서드
    @PostMapping("/{bBuildNumber}/upload-image")
    public ResponseEntity<String> uploadHouseImage(
            @PathVariable Long bBuildNumber,
            @RequestParam("file") MultipartFile file) {
        try {
            houseDetailService.uploadImage(bBuildNumber, file);
            return ResponseEntity.ok("Image uploaded successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (IOException e) {
            return ResponseEntity.internalServerError()
                    .body("Failed to upload image. Please try again.");
        }
    }
}
