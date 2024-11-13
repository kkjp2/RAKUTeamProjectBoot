package com.example.board_test.board.service;


import com.example.board_test.board.dto.request.FestivalBoardRequestDTO;
import com.example.board_test.board.dto.response.FestivalBoardResponseDTO;
import com.example.board_test.board.entity.FestivalBoardEntity;
import com.example.board_test.board.repository.FestivalBoardRepository;
import com.example.board_test.domain.member.entity.MemberEntity;
import com.example.board_test.domain.member.entity.enums.MemberRole;
import com.example.board_test.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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


    public Long createFestivalBoard(FestivalBoardRequestDTO festivalBoardRequestDTO, Long admin)
    {
        MemberEntity member=memberRepository.findById(admin)
                .orElseThrow(()-> new IllegalArgumentException("만들수가 없군요.흠."));
        if(!member.getRole().equals(MemberRole.ADMIN))
        {
            throw new SecurityException("오직 관리자만 작성이 가능해요");
        }
        festivalBoardRequestDTO.setMember(member);
        FestivalBoardEntity festivalBoard=festivalBoardRequestDTO.toEntity();
        festivalBoardRepository.save(festivalBoard);
        return festivalBoard.getF_id();
    }

    @Transactional(readOnly = true)
    public FestivalBoardResponseDTO findById(Long f_id)
    {
        FestivalBoardEntity festivalBoard=festivalBoardRepository.findById(f_id)
                .orElseThrow(()-> new IllegalArgumentException("찾을수 읎당~"));
        festivalBoardRepository.View(f_id);
        return new FestivalBoardResponseDTO(festivalBoard);
    }

    public List<FestivalBoardResponseDTO> getAllFestivalBoards()
    {
        return festivalBoardRepository.findAll()
                .stream()
                .map(FestivalBoardResponseDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long updateFestivalBoard(Long f_id, Long admin, FestivalBoardRequestDTO festivalBoardRequestDTO)
    {
        MemberEntity member=memberRepository.findById(admin)
                .orElseThrow(()-> new IllegalArgumentException("끼야야야야야약"));
        if(!member.getRole().equals(MemberRole.ADMIN))
        {
            throw new SecurityException("어떻게 들어오셧나요?");
        }
        FestivalBoardEntity festivalBoard=festivalBoardRepository.findById(f_id)
                .orElseThrow(()-> new IllegalArgumentException("음..못찾겠당.."));
        festivalBoard.update(festivalBoardRequestDTO.getTitle(),festivalBoardRequestDTO.getContent(),festivalBoardRequestDTO.getImage());
        return festivalBoard.getF_id();
    }

    @Transactional
    public void deleteFestivalBoard(Long f_id, Long admin)
    {
        MemberEntity member=memberRepository.findById(admin)
                .orElseThrow(()-> new IllegalArgumentException("잘못된 접근입니다만?"));
        if(!member.getRole().equals(MemberRole.ADMIN))
        {
            throw new SecurityException("당신이 삭제하면 안되거든요.");
        }
        festivalBoardRepository.deleteById(f_id);
    }

    @Transactional
    public void addLike(Long f_id, String nickname)
    {
        MemberEntity member=memberRepository.findById(nickname)
                .orElseThrow(()->new IllegalArgumentException("없"));
        festivalBoardRepository.Like(f_id,member);
    }




}
