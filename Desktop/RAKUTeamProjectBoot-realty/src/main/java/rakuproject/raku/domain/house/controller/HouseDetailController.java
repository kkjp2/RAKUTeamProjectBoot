package rakuproject.raku.domain.house.controller;

import kr.ac.ync.realty.domain.house.entity.HouseDetail;
import kr.ac.ync.realty.domain.house.service.HouseDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/houses")
@RequiredArgsConstructor
public class HouseDetailController {
    private final HouseDetailService service;

    @GetMapping
    public List<HouseDetail> getAll() {
        return service.findAll();
    }

    @PostMapping
    public HouseDetail create(@RequestBody HouseDetail houseDetail) {
        return service.save(houseDetail);
    }
}
