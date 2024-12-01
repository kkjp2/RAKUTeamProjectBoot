package rakuproject.raku.domain.move.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ReviewWithCompanyDTO {
    private Long reviewId;
    private String comment;
    private Long rating;
    private String region;
    private String price;
    private LocalDate serviceDate;
    private String companyName;
    private String fileName;
    private String folderPath;
    private String uuid;

    public ReviewWithCompanyDTO(Long reviewId, String comment, Long rating, String region, String price, LocalDate serviceDate,
                                String companyName, String fileName, String folderPath, String uuid) {
        this.reviewId = reviewId;
        this.comment = comment;
        this.rating = rating;
        this.region = region;
        this.price = price;
        this.serviceDate = serviceDate;
        this.companyName = companyName;
        this.fileName = fileName;
        this.folderPath = folderPath;
        this.uuid = uuid;
    }
}
