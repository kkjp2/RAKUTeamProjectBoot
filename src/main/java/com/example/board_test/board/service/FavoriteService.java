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


@Slf4j
@Service
@RequiredArgsConstructor
public class FavoriteService {
    @Autowired
    private final BoardRepository boardRepository;
    @Autowired
    private final FestivalBoardRepository festivalBoardRepository;
    @Autowired
    private final FavoriteRepository favoriteRepository;
    @Autowired
    private final MemberSecurity memberSecurity;
    @Autowired
    private final MemberRepository memberRepository;

    @Transactional
    public void addFavorite(FavoriteRequestDTO favoriteRequestDTO) {
        MemberEntity memberEntity = getMemberEntity(favoriteRequestDTO.getMemberId());

        if (favoriteRequestDTO.getBoardId() != null) {
            handleBoardFavorite(favoriteRequestDTO.getBoardId(), memberEntity);
        } else if (favoriteRequestDTO.getFestivalBoardId() != null) {
            handleFestivalBoardFavorite(favoriteRequestDTO.getFestivalBoardId(), memberEntity);
        } else {
            throw new IllegalArgumentException("요청 데이터가 유효하지 않습니다.");
        }
    }

    private MemberEntity getMemberEntity(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("회원 정보를 찾을 수 없습니다."));
    }

    private void handleBoardFavorite(Long boardId, MemberEntity memberEntity) {
        BoardEntity board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다. 게시글 ID: " + boardId));

        favoriteRepository.findByMemberAndBoard(memberEntity, board)
                .ifPresent(favorite -> {
                    throw new IllegalArgumentException("이미 추가된 게시글입니다.");
                });

        FavoriteEntity favorite = FavoriteEntity.builder()
                .member(memberEntity)
                .board(board)
                .build();
        favoriteRepository.save(favorite);
    }

    private void handleFestivalBoardFavorite(Long festivalBoardId, MemberEntity memberEntity) {
        FestivalBoardEntity festivalBoard = festivalBoardRepository.findById(festivalBoardId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 축제 게시글입니다. 축제 게시글 ID: " + festivalBoardId));

        favoriteRepository.findByMemberAndFestivalBoard(memberEntity, festivalBoard)
                .ifPresent(favorite -> {
                    throw new IllegalArgumentException("이미 추가된 축제 게시글입니다.");
                });

        FavoriteEntity favorite = FavoriteEntity.builder()
                .member(memberEntity)
                .festivalBoard(festivalBoard)
                .build();
        favoriteRepository.save(favorite);
    }
//    @Transactional
//    public void removeFavorite(Long board_id, String nickname)
//    {
//        MemberEntity member=memberRepository.findById(nickname)
//                .orElseThrow(()->new IllegalArgumentException("찾을 수없는 회원이군요!"));
//        BoardEntity board=boardRepository.findById(board_id)
//                .orElseThrow(()->new IllegalArgumentException("찾을 수없는 게시글이에요!"));
//        FavoriteEntity favorite=favoriteRepository.findByMemberAndBoard(member,board)
//                .orElseThrow(()-> new IllegalArgumentException("하하 이거 참 즐겨찾기에 추가할수 없어용~"));
//        favoriteRepository.delete(favorite);
//    }
//
//    @Transactional(readOnly = true)
//    public List<FavoriteEntity> getUserFavorites(String nickname)
//    {
//        MemberEntity member= memberRepository.findById(nickname)
//                .orElseThrow(()-> new IllegalArgumentException("찾을 수없어~~~용~"));
//        return favoriteRepository.findByMember(member);
//    }




}
