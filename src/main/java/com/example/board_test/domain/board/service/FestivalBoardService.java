package com.example.board_test.domain.board.service;


import com.example.board_test.domain.board.dto.request.FestivalRegisterRequest;
import com.example.board_test.domain.board.dto.response.FestivalBoardResponseDTO;
import com.example.board_test.domain.board.entity.FestivalBoardEntity;
import com.example.board_test.domain.board.repository.FestivalBoardRepository;
import com.example.board_test.domain.member.dto.MemberDTO;
import com.example.board_test.domain.member.entity.MemberEntity;
import com.example.board_test.domain.member.mapper.MemberMapper;
import com.example.board_test.domain.member.repository.MemberRepository;
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
public class FestivalBoardService {

    @Autowired
    private final FestivalBoardRepository festivalBoardRepository;

    @Autowired
    private final MemberRepository memberRepository;

    @Autowired
    private final MemberSecurity memberSecurity;
    
    
    //게시물 등록
    @Transactional
    public void register(FestivalRegisterRequest request)
    {
        MemberDTO memberDTO=memberSecurity.getMember();
        MemberEntity memberEntity = MemberMapper.createEntity(memberDTO);

        FestivalBoardEntity festivalBoardEntity=FestivalBoardEntity.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .image(request.getImage())
                .category(request.getCategory())
                .festivalComments(new ArrayList<>())
                .member(memberEntity)
                .build();
        festivalBoardRepository.save(festivalBoardEntity);
    }
    //게시물 상세 조회
    @Transactional(readOnly = true)
    public FestivalBoardResponseDTO findById(Long id)
    {
        FestivalBoardEntity festivalBoard=festivalBoardRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("not found"));
        return new FestivalBoardResponseDTO(festivalBoard);
    }

    //업데이트
    @Transactional
    public void update(FestivalRegisterRequest request)
    {
        MemberDTO memberDTO=memberSecurity.getMember();
        MemberEntity memberEntity=MemberMapper.createEntity(memberDTO);
        FestivalBoardEntity festivalBoardEntity=festivalBoardRepository.
                findById(request.getId())
                .orElseThrow(()->new IllegalArgumentException("not found"));
        festivalBoardEntity.update(request.getTitle(), request.getContent(), request.getImage());
    }
    public List<FestivalBoardResponseDTO> getAllFestivals()
    {
        return festivalBoardRepository.findAll()
                .stream()
                .map(FestivalBoardResponseDTO::new)
                .collect(Collectors.toList());
    }
    @Transactional
    public void deleteFestival(Long f_id)
    {
        festivalBoardRepository.deleteById(f_id);
    }

//    @Transactional
//    public void addLike(Long f_id, String nickname)
//    {
//        MemberEntity member=memberRepository.findById(nickname)
//                .orElseThrow(()->new IllegalArgumentException("없"));
//        festivalBoardRepository.Like(f_id,member);
//    }




}
