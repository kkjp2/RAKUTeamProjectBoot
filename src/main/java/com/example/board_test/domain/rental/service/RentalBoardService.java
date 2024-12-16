package com.example.board_test.domain.rental.service;

import com.example.board_test.domain.member.dto.MemberDTO;
import com.example.board_test.domain.member.entity.MemberEntity;
import com.example.board_test.domain.member.mapper.MemberMapper;
import com.example.board_test.domain.member.repository.MemberRepository;
import com.example.board_test.domain.rental.dto.RentalBoardDTO;
import com.example.board_test.domain.rental.dto.response.RentalResponseDTO;
import com.example.board_test.domain.rental.entity.RentalBoardEntity;
import com.example.board_test.domain.rental.repository.RentalBoardRepository;
import com.example.board_test.global.security.MemberSecurity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class RentalBoardService {

    @Autowired
    private final RentalBoardRepository rentalBoardRepository;

    @Autowired
    private final MemberRepository memberRepository;

    @Autowired
    private final MemberSecurity memberSecurity;

    @Transactional
    public void register(RentalBoardDTO rentalRequest)
    {
        MemberDTO memberDTO=memberSecurity.getMember();
        MemberEntity memberEntity= MemberMapper.createEntity(memberDTO);

        RentalBoardEntity rentalBoardEntity=RentalBoardEntity
                .builder().
                rentalTitle(rentalRequest.getRentalTitle()).
                rentalContent(rentalRequest.getRentalContent()).
                rentalImage(rentalRequest.getRentalImage()).
                rentalCategory(rentalRequest.getRentalCategory()).
                rentalAddress(rentalRequest.getRentalAddress()).
                rentalHomeType(rentalRequest.getRentalHomeType()).
                rentalPrice(rentalRequest.getRentalPrice()).
                startDate(rentalRequest.getStartDate()).
                endDate(rentalRequest.getEndDate()).
                reviewList(new ArrayList<>()).
                member(memberEntity).
                build();
        rentalBoardRepository.save(rentalBoardEntity);
    }

    @Transactional(readOnly = true)
    public RentalResponseDTO findById(Long id)
    {
        RentalBoardEntity rentalBoard=rentalBoardRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("not found"));
        return new RentalResponseDTO(rentalBoard);
    }
    @Transactional
    public void update(RentalBoardDTO request)
    {
        MemberDTO memberDTO=memberSecurity.getMember();
        MemberEntity memberEntity=MemberMapper.createEntity(memberDTO);
        RentalBoardEntity rentalBoardEntity=rentalBoardRepository.findById(request.getRbId())
                .orElseThrow(()-> new IllegalArgumentException("not found"));
        rentalBoardEntity.update(request.getRentalTitle(),request.getRentalContent(),
                request.getRentalImage(),request.getRentalPrice(),
                request.getStartDate(),request.getEndDate(),
                request.getRentalAddress(),
                request.getRentalHomeType());
    }

    public List<RentalResponseDTO> getAllRentals()
    {
        return rentalBoardRepository.findAll()
                .stream()
                .map(RentalResponseDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteRentalBoard(Long rbId){
        rentalBoardRepository.deleteById(rbId);
    }


}
