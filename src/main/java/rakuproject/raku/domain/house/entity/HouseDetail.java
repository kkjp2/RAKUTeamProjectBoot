package rakuproject.raku.domain.house.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "BuildData") // 데이터베이스 테이블 이름과 매핑
public class HouseDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "B_buildNumber") // 데이터베이스 컬럼 이름과 매핑
    private Long buildNumber; // 필드 이름은 반드시 소문자로 시작

    @Column(name = "B_name", nullable = false)
    private String name; // 매물명

    @Column(name = "B_building_type", nullable = false)
    private String buildingType; // 건축물 종류

    @Column(name = "B_floors", nullable = false)
    private Integer floors; // 건축 층수

    @Column(name = "B_room_type", nullable = false)
    private String roomType; // 방 구분

    @Column(name = "B_is_new", nullable = false)
    private Boolean isNew; // 구/신축 구분

    @Column(name = "B_sale_type", nullable = false)
    private String saleType; // 월세/매매/월세+매매

    @Column(name = "B_rent_price")
    private String rentPrice; // 월세 값

    @Column(name = "B_sale_price")
    private String salePrice; // 매매 값

    @Column(name = "B_is_duplex", nullable = false)
    private Boolean isDuplex; // 복층/단층 구분

    @Column(name = "B_prefecture", nullable = false)
    private String prefecture; // 지방

    @Column(name = "B_address", nullable = false)
    private String address; // 주소

    @Column(name = "B_detailed_address", nullable = false)
    private String detailedAddress; // 상세 주소

    @Column(name = "B_ranking")
    private String ranking; // 랭킹

    @Column(name = "B_deposit")
    private String deposit; // 보증금

    @Column(name = "B_previous_use")
    private String previousUse; // 이전 사용처

    @Column(name = "B_construction_date", nullable = false)
    private LocalDate constructionDate; // 건축년월

    @Column(name = "B_tags")
    private String tags; // 태그

    @Column(name = "B_building_size", nullable = false)
    private String buildingSize; // 건축물 크기

    @Column(name = "realty_member_key", nullable = false)
    private int realtyMemberKey; // 부동산 회원 키
}
