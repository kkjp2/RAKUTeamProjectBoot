package rakuproject.raku.domain.company.dto;


import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Builder
@Getter
@Setter
public class CompanyDTO {
    private Long realtyCompanyKey;
    private String license;
    private String name;
    private MultipartFile img;
    private String number;
    private String zipcode;
    private String address;
    private String openhour;
    private String holiday;
    private String hompage;
    private String content;
    private String fax;
    private MultipartFile map;

}
