package rakuproject.raku.domain.house.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rakuproject.raku.domain.house.entity.HouseDetail;
import rakuproject.raku.domain.house.repository.HouseDetailRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class HouseDetailService {

    private final HouseDetailRepository houseDetailRepository;

    public HouseDetailService(HouseDetailRepository houseDetailRepository) {
        this.houseDetailRepository = houseDetailRepository;
    }

    /**
     * 모든 HouseDetail 데이터 조회
     */
    public List<HouseDetail> getAllHouseDetails() {
        return houseDetailRepository.findAll();
    }

    /**
     * bBuildNumber로 특정 데이터 조회
     *
     * @param bBuildNumber - 검색할 bBuildNumber
     * @return Optional<HouseDetail>
     */
    public Optional<HouseDetail> getHouseDetailByBBuildNumber(Long bBuildNumber) {
        return houseDetailRepository.findByBuildNumber(bBuildNumber);
    }

    /**
     * 새로운 HouseDetail 데이터 추가
     *
     * @param houseDetail - 저장할 데이터
     * @return 저장된 HouseDetail 객체
     */
    public HouseDetail createHouseDetail(HouseDetail houseDetail) {
        return houseDetailRepository.save(houseDetail);
    }

    /**
     * 기존 HouseDetail 데이터 업데이트
     *
     * @param id          - 업데이트할 데이터의 ID
     * @param updatedData - 업데이트할 새로운 데이터
     * @return Optional<HouseDetail> - 업데이트된 데이터
     */
    public Optional<HouseDetail> updateHouseDetail(Long id, HouseDetail updatedData) {
        return houseDetailRepository.findById(id).map(existing -> {
            existing.setName(updatedData.getName());
            existing.setRentPrice(updatedData.getRentPrice());
            existing.setSalePrice(updatedData.getSalePrice());
            existing.setBuildingType(updatedData.getBuildingType());
            existing.setFloors(updatedData.getFloors());
            // 필요한 필드 추가 업데이트
            return houseDetailRepository.save(existing);
        });
    }

    /**
     * 특정 ID의 HouseDetail 데이터 삭제
     *
     * @param id - 삭제할 데이터의 ID
     */
    public void deleteHouseDetail(Long id) {
        houseDetailRepository.deleteById(id);
    }
}
