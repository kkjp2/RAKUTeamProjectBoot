package rakuproject.raku.domain.rental.dto.response;


import lombok.Getter;
import lombok.NoArgsConstructor;
import rakuproject.raku.domain.rental.entity.RentalBoardEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class RentalResponseDTO {
    private Long rbId;
    private String rentalTitle;
    private String rentalContent;
    private String rentalImage;
    private int rentalCategory;
    private int rentalPrice;
    private String rentalAddress;
    private String rentalHomeType;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int rentalView;
    private String member;
    private List<RentalReviewResponseDTO> reviews;

    public RentalResponseDTO(RentalBoardEntity rentalBoard)
    {
        this.rbId=rentalBoard.getRbId();
        this.rentalContent=rentalBoard.getRentalContent();
        this.rentalTitle=rentalBoard.getRentalTitle();
        this.rentalImage=rentalBoard.getRentalImage();
        this.rentalCategory=rentalBoard.getRentalCategory();
        this.rentalPrice=rentalBoard.getRentalPrice();
        this.rentalAddress=rentalBoard.getRentalAddress();
        this.rentalHomeType=rentalBoard.getRentalHomeType();
        this.startDate=rentalBoard.getStartDate();
        this.endDate=rentalBoard.getEndDate();
        this.rentalView=rentalBoard.getRentalViewCnt();
        this.member=rentalBoard.getMember().getNick();
        this.reviews=rentalBoard.getReviewList().stream().map(RentalReviewResponseDTO::new).collect(Collectors.toList());




    }

}
