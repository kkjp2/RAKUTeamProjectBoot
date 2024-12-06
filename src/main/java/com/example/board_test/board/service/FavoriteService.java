package com.example.board_test.board.service;


import com.example.board_test.board.dto.request.FavoriteRequestDTO;
import com.example.board_test.board.entity.BoardEntity;
import com.example.board_test.board.entity.FavoriteEntity;
import com.example.board_test.board.entity.FestivalBoardEntity;
import com.example.board_test.board.repository.BoardRepository;
import com.example.board_test.board.repository.FavoriteRepository;
import com.example.board_test.board.repository.FestivalBoardRepository;
import com.example.board_test.domain.member.entity.MemberEntity;
import com.example.board_test.domain.member.repository.MemberRepository;
import com.example.board_test.global.security.MemberSecurity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class FavoriteService {
    @Autowired
    private final MemberRepository memberRepository;
    @Autowired
    private final BoardRepository boardRepository;
    @Autowired
    private final FestivalBoardRepository festivalBoardRepository;
    @Autowired
    private final FavoriteRepository favoriteRepository;
    @Autowired
    private final MemberSecurity memberSecurity;


//    @Transactional
//    public void register(FavoriteRequestDTO favoriteRequestDTO)
//    {
//        MemberDTO memberDTO=memberSecurity.getMember();
//        MemberEntity memberEntity= MemberMapper.createEntity(memberDTO);
//
//        FavoriteEntity favoriteEntity=FavoriteEntity.builder()
//                .board(favoriteRequestDTO.getBoardId())
//                .festivalBoard(favoriteRequestDTO.getFestivalBoardId())
//                .member(memberEntity)
//                .build();
//        favoriteRepository.save(favoriteEntity);
//    }



    // 즐겨찾기 추가
    @Transactional
    public void addFavorite(@RequestBody FavoriteRequestDTO favoriteRequestDTO)
    {

        MemberEntity member=memberRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("찾을수 업는 회원"));

        BoardEntity board=boardRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("찾을 수없는 게시글"));

        FestivalBoardEntity festivalBoard=festivalBoardRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("NOT FOUND"));

        if(favoriteRepository.existsByMemberAndBoard(member,board))
        {
            throw new IllegalArgumentException("이미 추가된 게시글입니다~");
        }
        FavoriteEntity favorite= FavoriteEntity.builder()
                .member(member)
                .board(board)
                .festivalBoard(festivalBoard)
                .build();
        favoriteRepository.save(favorite);

    }

    @Transactional
    public void removeFavorite(Long board_id, String nickname)
    {
        MemberEntity member=memberRepository.findById(nickname)
                .orElseThrow(()->new IllegalArgumentException("찾을 수없는 회원이군요!"));
        BoardEntity board=boardRepository.findById(board_id)
                .orElseThrow(()->new IllegalArgumentException("찾을 수없는 게시글이에요!"));
        FavoriteEntity favorite=favoriteRepository.findByMemberAndBoard(member,board)
                .orElseThrow(()-> new IllegalArgumentException("하하 이거 참 즐겨찾기에 추가할수 없어용~"));
        favoriteRepository.delete(favorite);
    }

    @Transactional(readOnly = true)
    public List<FavoriteEntity> getUserFavorites(String nickname)
    {
        MemberEntity member= memberRepository.findById(nickname)
                .orElseThrow(()-> new IllegalArgumentException("찾을 수없어~~~용~"));
        return favoriteRepository.findByMember(member);
    }




}
