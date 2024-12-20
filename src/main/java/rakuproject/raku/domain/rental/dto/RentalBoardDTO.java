package rakuproject.raku.domain.rental.dto;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class RentalBoardDTO {
    private Long rbId;
    private String rentalTitle;
    private String rentalContent;
    private String rentalImage;
    private int rentalPrice;
    private String rentalAddress;
    private String rentalHomeType;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int rentalCategory;
}
