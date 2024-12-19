package rakuproject.raku.domain.rental.dto.response;


import lombok.Getter;
import lombok.NoArgsConstructor;
import rakuproject.raku.domain.rental.entity.RentalReservationEntity;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class RentalReservationResponseDTO {
    private Long resId;
    private LocalDateTime resDate;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int resPrice;
    private String resAddress;
    private String resHomeType;
    private Long rentalBoard;
    private String member;

    public RentalReservationResponseDTO(RentalReservationEntity reservation)
    {
        this.resId=reservation.getResId();
        this.resDate=reservation.getResDate();
        this.startDate=reservation.getStartDate();
        this.endDate=reservation.getEndDate();
        this.resPrice=reservation.getResPrice();
        this.resAddress=reservation.getResAddress();
        this.resHomeType=reservation.getResHomeType();
        this.rentalBoard=reservation.getRentalBoard().getRbId();
        this.member=reservation.getMember().getNick();



    }


}
