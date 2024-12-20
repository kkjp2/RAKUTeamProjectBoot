package rakuproject.raku.domain.house.controller;

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

    @GetMapping
    public ResponseEntity<List<HouseDetailDTO>> getAllHouseDetails() {
        List<HouseDetail> houseDetails = houseDetailService.getAllHouseDetails();
        List<HouseDetailDTO> houseDetailDtos = houseDetails.stream()
                .map(HouseDetailDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(houseDetailDtos);
    }

    @GetMapping("/{bBuildNumber}")
    public ResponseEntity<HouseDetailDTO> getHouseDetail(@PathVariable Long bBuildNumber) {
        return houseDetailService.getHouseDetailByBBuildNumber(bBuildNumber)
                .map(houseDetail -> ResponseEntity.ok(new HouseDetailDTO(houseDetail)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<HouseDetailDTO> createHouseDetail(@RequestBody HouseDetail houseDetail) {
        HouseDetail createdHouse = houseDetailService.createHouseDetail(houseDetail);
        return ResponseEntity.ok(new HouseDetailDTO(createdHouse));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HouseDetailDTO> updateHouseDetail(
            @PathVariable Long id,
            @RequestBody HouseDetail updatedData) {
        return houseDetailService.updateHouseDetail(id, updatedData)
                .map(updatedHouse -> ResponseEntity.ok(new HouseDetailDTO(updatedHouse)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHouseDetail(@PathVariable Long id) {
        houseDetailService.deleteHouseDetail(id);
        return ResponseEntity.noContent().build();
    }

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
