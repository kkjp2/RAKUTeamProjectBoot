package rakuproject.raku.domain.house.dto;

import lombok.Data;

import java.util.List;


//DTO:변수이름 기반으로 데이터베이스 내 입력값을 입출력(리퀘스트,리스폰스)역할을 담당 ,


@Data
public class HouseDetailDTO {
    private String name;
    private String address;
    private String size;
    private String rent;
    private String type;
    private String floors;
    private String ymd;
    private String shikikin;
    private String reikin;
    private String prvusage;
    private String concierge;
    private String sell;
    private String buildNum;
    private List<String> images;
}
