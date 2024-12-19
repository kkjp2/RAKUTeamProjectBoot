package rakuproject.raku.domain.house.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import rakuproject.raku.domain.house.entity.HouseDetail;
import rakuproject.raku.domain.house.repository.HouseDetailRepository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class HouseDetailService {

    private final HouseDetailRepository houseDetailRepository;

    public HouseDetailService(HouseDetailRepository houseDetailRepository) {
        this.houseDetailRepository = houseDetailRepository;
    }

    public List<HouseDetail> getAllHouseDetails() {
        return houseDetailRepository.findAll();
    }

    public Optional<HouseDetail> getHouseDetailByBBuildNumber(Long bBuildNumber) {
        return houseDetailRepository.findByBuildNumber(bBuildNumber);
    }

    public HouseDetail createHouseDetail(HouseDetail houseDetail) {
        return houseDetailRepository.save(houseDetail);
    }

    public Optional<HouseDetail> updateHouseDetail(Long id, HouseDetail updatedData) {
        return houseDetailRepository.findById(id).map(existing -> {
            existing.setName(updatedData.getName());
            existing.setRentPrice(updatedData.getRentPrice());
            existing.setSalePrice(updatedData.getSalePrice());
            existing.setBuildingType(updatedData.getBuildingType());
            existing.setFloors(updatedData.getFloors());
            return houseDetailRepository.save(existing);
        });
    }

    public void deleteHouseDetail(Long id) {
        houseDetailRepository.deleteById(id);
    }

    public void uploadImage(Long bBuildNumber, MultipartFile file) throws IOException {
        Optional<HouseDetail> houseDetailOptional = houseDetailRepository.findByBuildNumber(bBuildNumber);
        if (houseDetailOptional.isEmpty()) {
            throw new IllegalArgumentException("House with ID " + bBuildNumber + " not found.");
        }
        HouseDetail houseDetail = houseDetailOptional.get();
        houseDetail.setPicture(file.getBytes());
        houseDetailRepository.save(houseDetail);
    }
}
