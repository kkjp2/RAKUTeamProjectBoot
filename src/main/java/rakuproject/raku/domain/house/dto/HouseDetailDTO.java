package rakuproject.raku.domain.house.dto;

import lombok.Getter;
import lombok.Setter;
import rakuproject.raku.domain.house.entity.HouseDetail;

import java.time.LocalDate;

@Getter
@Setter
public class HouseDetailDTO {
    private Long buildNumber;
    private String name;
    private String buildingType;
    private Integer floors;
    private String roomType;
    private Boolean isNew;
    private String saleType;
    private String rentPrice;
    private String salePrice;
    private Boolean isDuplex;
    private String prefecture;
    private String address;
    private String detailedAddress;
    private String fullAddress;
    private String ranking;
    private String deposit;
    private String previousUse;
    private LocalDate constructionDate;
    private String tags;
    private String buildingSize;
    private byte[] picture; // 그림 데이터를 위한 필드 추가

    public HouseDetailDTO(HouseDetail houseDetail) {
        this.buildNumber = houseDetail.getBuildNumber();
        this.name = houseDetail.getName();
        this.buildingType = houseDetail.getBuildingType();
        this.floors = houseDetail.getFloors();
        this.roomType = houseDetail.getRoomType();
        this.isNew = houseDetail.getIsNew();
        this.saleType = houseDetail.getSaleType();
        this.rentPrice = houseDetail.getRentPrice();
        this.salePrice = houseDetail.getSalePrice();
        this.isDuplex = houseDetail.getIsDuplex();
        this.prefecture = houseDetail.getPrefecture();
        this.address = houseDetail.getAddress();
        this.detailedAddress = houseDetail.getDetailedAddress();
        this.fullAddress = houseDetail.getFullAddress();
        this.ranking = houseDetail.getRanking();
        this.deposit = houseDetail.getDeposit();
        this.previousUse = houseDetail.getPreviousUse();
        this.constructionDate = houseDetail.getConstructionDate();
        this.tags = houseDetail.getTags();
        this.buildingSize = houseDetail.getBuildingSize();
        this.picture = houseDetail.getPicture(); // 그림 데이터 설정
    }
}

