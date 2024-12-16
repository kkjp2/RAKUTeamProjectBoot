package com.example.board_test.domain.rental.dto.request;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class RentalFavoriteRequestDTO {
    private Long rentalFavId;
    private Long memberId;
    private Long rentalBoardId;
}
