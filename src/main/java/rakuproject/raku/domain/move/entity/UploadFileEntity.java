package rakuproject.raku.domain.move.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "upload_file")
public class UploadFileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    private Long id;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "uuid")
    private String uuid;

    @Column(name = "folder_path")
    private String folderPath;  // 저장되는 파일 경로.

    @Column(name = "upload_date")
    private LocalDate uploadDate;  // 올린 날.

    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "company_id", foreignKey = @ForeignKey(name = "fk_company_information"), nullable = true)
    @JsonBackReference
    private MoveCompanyEntity companyId;

    public String getUrl() {
        return "http://localhost:8080/files/" + folderPath + "/" + fileName;  // 기본적으로 URL 반환
    }

}