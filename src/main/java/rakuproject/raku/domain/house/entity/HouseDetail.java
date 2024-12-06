package rakuproject.raku.domain.house.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
public class HouseDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
    private Date ymd;
    private String size;
    private String rent;
    private String type;
    private String floors;
    private String concierge;
    private int reikin;
    private String prvusage;
    private String conciergeComent;
    private String dpsin;
    private String buildNum;
    private int sell;
    private String shikikin;
    private int views;
    private int recommendations;

    @ElementCollection
    private List<String> tag;
}
