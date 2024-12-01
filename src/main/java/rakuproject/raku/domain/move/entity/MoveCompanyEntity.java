package rakuproject.raku.domain.move.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "company_information")
public class MoveCompanyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "CEO")
    private String ceo;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "address")
    private String address;

    @Column(name = "detailed_address")
    private String detailedAddress;

    @Column(name = "business_number")
    private String businessNumber;

    @OneToOne
    @JoinColumn(name = "file_id", referencedColumnName = "file_id", foreignKey = @ForeignKey(name = "fk_upload_file"), nullable = true)
    @JsonManagedReference
    private UploadFileEntity fileId;

    @OneToMany(mappedBy = "company") // 假设是映射
    @JsonManagedReference
    private List<MoveReviewEntity> reviews;

    @Column(name = "service")
    private String service;

    @Column(name = "city")
    private String moveCity;

    @Column(name = "introduction")
    private String introduction;

}
