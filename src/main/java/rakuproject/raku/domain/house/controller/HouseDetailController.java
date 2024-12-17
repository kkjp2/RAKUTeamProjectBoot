package rakuproject.raku.domain.house.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rakuproject.raku.domain.house.entity.HouseDetail;
import rakuproject.raku.domain.house.service.HouseDetailService;

import java.util.List;

@RestController
@RequestMapping("/api/houses")
public class HouseDetailController {

    private final HouseDetailService houseDetailService;

    public HouseDetailController(HouseDetailService houseDetailService) {
        this.houseDetailService = houseDetailService;
    }

    @GetMapping
    public ResponseEntity<List<HouseDetail>> getAllHouseDetails() {
        return ResponseEntity.ok(houseDetailService.getAllHouseDetails());
    }

    @GetMapping("/{bBuildNumber}")
    public ResponseEntity<HouseDetail> getHouseDetail(@PathVariable Long bBuildNumber) {
        return houseDetailService.getHouseDetailByBBuildNumber(bBuildNumber)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("")
    public ResponseEntity<HouseDetail> createHouseDetail(@RequestBody HouseDetail houseDetail) {
        return ResponseEntity.ok(houseDetailService.createHouseDetail(houseDetail));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HouseDetail> updateHouseDetail(@PathVariable Long id, @RequestBody HouseDetail updatedData) {
        return houseDetailService.updateHouseDetail(id, updatedData)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHouseDetail(@PathVariable Long id) {
        houseDetailService.deleteHouseDetail(id);
        return ResponseEntity.noContent().build();
    }
}
