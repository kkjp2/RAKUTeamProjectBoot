package rakuproject.raku.domain.house.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rakuproject.raku.domain.house.entity.HouseDetail;
import rakuproject.raku.domain.house.repository.HouseDetailRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HouseDetailService {
    private final HouseDetailRepository repository;

    public List<HouseDetail> findAll() {
        return repository.findAll();
    }

    public HouseDetail save(HouseDetail houseDetail) {
        return repository.save(houseDetail);
    }
}
