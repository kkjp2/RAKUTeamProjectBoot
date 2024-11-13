package com.example.board_test.board.service;


import com.example.board_test.board.dto.request.BoardRequestDTO;
import com.example.board_test.board.dto.response.BoardResponseDTO;
import com.example.board_test.board.entity.BoardEntity;
import com.example.board_test.board.repository.BoardRepository;
import com.example.board_test.domain.member.entity.MemberEntity;
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
public class BoardService {

    @Autowired
    private final BoardRepository boardRepository;

    @Autowired
    private final MemberRepository memberRepository;


    @Transactional
    public Long createBoard(BoardRequestDTO boardRequestDTO, String nickname)
    {
        MemberEntity member=memberRepository.findById(nickname)
                .orElseThrow(()->new IllegalArgumentException("Warning!!!!Warning!!!!!!"));
        boardRequestDTO.setMember(member);
        BoardEntity boardEntity=boardRequestDTO.toEntity();
        boardRepository.save(boardEntity);
        return boardEntity.getN_id();
    }

    //게시물 조회
    @Transactional(readOnly = true)
    public BoardResponseDTO findById(Long n_id)
    {
        BoardEntity board= boardRepository.findById(n_id)
                .orElseThrow(()-> new IllegalArgumentException("게시글을 찾을수없거나 삭제되었습니다:("));
        boardRepository.View(n_id);
        return new BoardResponseDTO(board);
    }
    //게시물 리스트
    public List<BoardResponseDTO> getAllBoards()
    {
        return boardRepository.findAll()
                .stream()
                .map(BoardResponseDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long updateBoard(Long n_id, BoardRequestDTO dto)
    {
        BoardEntity boardEntity=boardRepository.findById(n_id)
                .orElseThrow(()->new IllegalArgumentException("not found not found"));
        boardEntity.update(dto.getTitle(),dto.getContent(),dto.getImage());
        return boardEntity.getN_id();
    }
    @Transactional
    public void deleteBoard(Long n_id)
    {
        boardRepository.deleteById(n_id);
    }

    @Transactional
    public void addLike(Long n_id, String nickname)
    {
        MemberEntity member= memberRepository.findById(nickname)
                .orElseThrow(()->new IllegalArgumentException("없음"));
        boardRepository.Like(n_id,member);
    }

    public void addDisLike(Long n_id, String nickname)
    {
        MemberEntity member=memberRepository.findById(nickname)
                .orElseThrow(()->new IllegalArgumentException("없음"));
        boardRepository.DisLike(n_id,member);
    }




}
