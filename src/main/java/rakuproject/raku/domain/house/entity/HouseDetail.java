package rakuproject.raku.domain.house.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "BuildData")
public class HouseDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "B_buildNumber")
    private Long buildNumber;

    @Column(name = "B_name", nullable = false)
    private String name;

    @Column(name = "B_building_type", nullable = false)
    private String buildingType;

    @Column(name = "B_floors", nullable = false)
    private Integer floors;

    @Column(name = "B_room_type", nullable = false)
    private String roomType;

    @Column(name = "B_is_new", nullable = false)
    private Boolean isNew;

    @Column(name = "B_sale_type", nullable = false)
    private String saleType;

    @Column(name = "B_rent_price")
    private String rentPrice;

    @Column(name = "B_sale_price")
    private String salePrice;

    @Column(name = "B_is_duplex", nullable = false)
    private Boolean isDuplex;

    @Column(name = "B_prefecture", nullable = false)
    private String prefecture;

    @NotNull
    @Column(name = "B_address", nullable = false)
    private String address;

    @NotNull
    @Column(name = "B_detailed_address", nullable = false)
    private String detailedAddress;

    @Column(name = "B_ranking")
    private String ranking;

    @Column(name = "B_deposit")
    private String deposit;

    @Column(name = "B_previous_use")
    private String previousUse;

    @Column(name = "B_construction_date", nullable = false)
    private LocalDate constructionDate;

    @Column(name = "B_tags")
    private String tags;

    @Column(name = "B_building_size", nullable = false)
    private String buildingSize;

    @Column(name = "realty_member_key", nullable = false)
    private int realtyMemberKey;

    @Lob
    @Column(name = "B_picture", nullable = true)
    private byte[] picture; // 그림 데이터를 저장하는 필드 추가

    // 전체 주소 반환 메서드 추가
    public String getFullAddress() {
        return address + " " + detailedAddress;
    }

    public void setPicturePath(String filePath) {
    }
}
