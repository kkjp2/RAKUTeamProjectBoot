package com.example.board_test.board.service;


import com.example.board_test.board.entity.BoardEntity;
import com.example.board_test.board.entity.FavoriteEntity;
import com.example.board_test.board.repository.BoardRepository;
import com.example.board_test.board.repository.FavoriteRepository;
import com.example.board_test.domain.member.entity.MemberEntity;
import com.example.board_test.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private final FavoriteRepository favoriteRepository;


    // 즐겨찾기 추가
    @Transactional
    public Long addFavorite(String nickname, Long board_id)
    {
        MemberEntity member=memberRepository.findById(nickname)
                .orElseThrow(()->new IllegalArgumentException("찾을수 업는 회원"));
        BoardEntity board=boardRepository.findById(board_id)
                .orElseThrow(()->new IllegalArgumentException("찾을 수없는 게시글"));

        if(favoriteRepository.existsMemberAndBoard(member,board))
        {
            throw new IllegalArgumentException("이미 추가된 게시글입니다~");
        }
        FavoriteEntity favorite= FavoriteEntity.builder()
                .member(member)
                .board(board)
                .build();
        favoriteRepository.save(favorite);
        return favorite.getFav_id();
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
