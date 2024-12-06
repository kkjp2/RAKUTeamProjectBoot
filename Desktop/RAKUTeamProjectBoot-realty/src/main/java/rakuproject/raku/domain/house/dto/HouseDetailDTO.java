package rakuproject.raku.domain.house.dto;

import lombok.Data;

import java.util.List;
//

////
@Data
public class HouseDetailDTO {
    private String name;
    private String address;
    private String size;
    private String rent;
    private List<String> tag;
}
