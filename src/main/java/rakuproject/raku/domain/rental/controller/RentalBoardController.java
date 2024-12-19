package rakuproject.raku.domain.rental.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import rakuproject.raku.domain.rental.dto.RentalBoardDTO;
import rakuproject.raku.domain.rental.dto.response.RentalResponseDTO;
import rakuproject.raku.domain.rental.service.RentalBoardService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/rental")
public class RentalBoardController {
    private final RentalBoardService rentalBoardService;

    @PostMapping
    public ResponseEntity register(@RequestBody RentalBoardDTO request)
    {
        rentalBoardService.register(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity findRentalById(@PathVariable("id") Long id)
    {
        RentalResponseDTO rentalBoard=rentalBoardService.findById(id);
        return ResponseEntity.ok(rentalBoard);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateRentalBoard(@PathVariable("id") Long id, @RequestBody RentalBoardDTO request)
    {
        request.setRbId(id);
        rentalBoardService.update(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<RentalResponseDTO>> getAllRentalBoards()
    {
        List<RentalResponseDTO> rentals=rentalBoardService.getAllRentals();
        return ResponseEntity.ok(rentals);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRentalBoard(@PathVariable("id")Long id)
    {
        rentalBoardService.deleteRentalBoard(id);
        return ResponseEntity.noContent().build();
    }

}
