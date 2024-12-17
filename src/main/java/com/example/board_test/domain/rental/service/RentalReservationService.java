package com.example.board_test.domain.rental.service;

import com.example.board_test.domain.member.dto.MemberDTO;
import com.example.board_test.domain.member.entity.MemberEntity;
import com.example.board_test.domain.member.mapper.MemberMapper;
import com.example.board_test.domain.member.repository.MemberRepository;
import com.example.board_test.domain.rental.dto.response.RentalReservationResponseDTO;
import com.example.board_test.domain.rental.entity.RentalBoardEntity;
import com.example.board_test.domain.rental.entity.RentalReservationEntity;
import com.example.board_test.domain.rental.repository.RentalBoardRepository;
import com.example.board_test.domain.rental.repository.RentalReservationRepository;
import com.example.board_test.global.security.MemberSecurity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
@Slf4j
public class RentalReservationService {
    @Autowired
    private final RentalReservationRepository rentalReservationRepository;
    @Autowired
    private final RentalBoardRepository rentalBoardRepository;
    @Autowired
    private final MemberRepository memberRepository;
    @Autowired
    private final MemberSecurity memberSecurity;

    @Transactional
    public RentalReservationResponseDTO register(Long rbId)
    {
        MemberDTO memberDTO=memberSecurity.getMember();
        MemberEntity memberEntity= MemberMapper.createEntity(memberDTO);

        RentalBoardEntity rentalBoard=rentalBoardRepository.findById(rbId)
                .orElseThrow(()->new IllegalArgumentException("해당 렌탈 게시글이 존재하지 않습니다."));

        RentalReservationEntity reservation=RentalReservationEntity
                .builder().
                resDate(LocalDateTime.now()).
                startDate(rentalBoard.getStartDate()).
                endDate(rentalBoard.getEndDate()).
                resPrice(rentalBoard.getRentalPrice()).
                resAddress(rentalBoard.getRentalAddress()).
                resHomeType(rentalBoard.getRentalHomeType()).
                rentalBoard(rentalBoard).
                member(memberEntity).
                build();
        rentalReservationRepository.save(reservation);
        return new RentalReservationResponseDTO(reservation);
    }




}
