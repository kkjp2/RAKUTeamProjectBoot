package com.example.board_test.domain.rental.controller;

import com.example.board_test.domain.rental.dto.response.RentalReservationResponseDTO;
import com.example.board_test.domain.rental.service.RentalReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reservation")
public class RentalReservationController {

    private final RentalReservationService rentalReservationService;


    @PostMapping("/{rentalBoardId}")
    public ResponseEntity<RentalReservationResponseDTO> register(
            @PathVariable Long rbId)
    {
        RentalReservationResponseDTO reservation=rentalReservationService.register(rbId);
        return ResponseEntity.ok(reservation);
    }


}
