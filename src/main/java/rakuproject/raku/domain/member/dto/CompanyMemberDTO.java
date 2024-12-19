package rakuproject.raku.domain.member.dto;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import rakuproject.raku.domain.company.entity.CompanyEntity;

@Builder
@Getter
@Setter
public class CompanyMemberDTO {

    private Long realtyMemberKey;
    private Long realtyCompanyKey;
    private String name;
    private String content;
    private int sex;
    private int age;
    private int experience;
    private String id;
    private String pwd;
    private MultipartFile img;


}
