package com.example.board_test.domain.board.service;


import com.example.board_test.domain.board.dto.request.BoardRegisterRequest;
import com.example.board_test.domain.board.dto.response.BoardResponseDTO;
import com.example.board_test.domain.board.entity.BoardEntity;
import com.example.board_test.domain.board.repository.BoardRepository;
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
public class BoardService {

    @Autowired
    private final BoardRepository boardRepository;

    @Autowired
    private final MemberRepository memberRepository;

    @Autowired
    private final MemberSecurity memberSecurity;

    @Transactional
    public void register(BoardRegisterRequest request){
        MemberDTO memberDTO = memberSecurity.getMember();
        MemberEntity memberEntity = MemberMapper.createEntity(memberDTO);

        BoardEntity boardEntity = BoardEntity.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .image(request.getImage())
                .category(request.getCategory())
                .commentList(new ArrayList<>())
                .member(
                        memberEntity
                ).build();
        boardRepository.save(boardEntity);
    }
    //게시물 조회
    @Transactional(readOnly = true)
    public BoardResponseDTO findById(Long id)
    {
        BoardEntity board= boardRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("게시글을 찾을수없거나 삭제되었습니다:("));
        return new BoardResponseDTO(board);
    }

//    @Transactional
//    public Long updateBoard(Long n_id, BoardRequestDTO dto)
//    {
//        BoardEntity boardEntity=boardRepository.findById(n_id)
//                .orElseThrow(()->new IllegalArgumentException("not found not found"));
//        boardEntity.update(dto.getTitle(),dto.getContent(),dto.getImage());
//        return boardEntity.getNId();
//    }


    //업데이트
    @Transactional
    public void update(BoardRegisterRequest request)
    {
        MemberDTO memberDTO=memberSecurity.getMember();
        MemberEntity memberEntity= MemberMapper.createEntity(memberDTO);
        BoardEntity boardEntity=boardRepository.findById(request.getId())
                .orElseThrow(()-> new IllegalArgumentException("not found"));
//        BoardEntity boardEntity= BoardEntity.builder().title(request.getTitle())
//            .content(request.getContent())
//            .image(request.getImage()).
//                build();
        boardEntity.update(request.getTitle(), request.getContent(), request.getImage());
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
