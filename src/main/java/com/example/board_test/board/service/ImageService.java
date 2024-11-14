package com.example.board_test.board.service;


import com.example.board_test.board.dto.request.ImageRequestDTO;
import com.example.board_test.board.dto.response.ImageResponseDTO;
import com.example.board_test.board.entity.BoardEntity;
import com.example.board_test.board.entity.FestivalBoardEntity;
import com.example.board_test.board.repository.BoardRepository;
import com.example.board_test.board.repository.FestivalBoardRepository;
import com.example.board_test.board.repository.ImageRepository;
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
public class ImageService {

    @Autowired
    private final ImageRepository imageRepository;
    @Autowired
    private final BoardRepository boardRepository;
    @Autowired
    private final FestivalBoardRepository festivalBoardRepository;
    @Autowired
    private final MemberRepository memberRepository;

    @Transactional
    public Long addImage(ImageRequestDTO imageRequestDTO)
    {
        MemberEntity member= memberRepository.findById(imageRequestDTO.getMemberId())
                .orElseThrow(()-> new IllegalArgumentException("멤버 없음"));

        if((imageRequestDTO.getBoardId() != null) ==(imageRequestDTO.getFestivalBoardId() != null))
        {
            throw new IllegalArgumentException("이미지는 한가지에만 할당하세요");
        }
        return null;
    }





//    @Transactional
//    public Long saveImage(String imageUrl, MemberEntity member, BoardEntity board, FestivalBoardEntity festivalBoard)
//    {
//        ImageEntity image=ImageEntity.builder().
//                image(imageUrl)
//                .member(member)
//                .board(board)
//                .festivalBoard(festivalBoard)
//                .build();
//        return imageRepository.save(image).getImg_id();
//    }

    @Transactional(readOnly = true)
    public List<ImageResponseDTO> getImagesByBoard(BoardEntity board)
    {
        return imageRepository.findByBoard(board)
                .stream()
                .map(ImageResponseDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ImageResponseDTO> getImagesByFestivalBoard(FestivalBoardEntity festivalBoard)
    {
        return imageRepository.findByFestivalBoard(festivalBoard)
                .stream()
                .map(ImageResponseDTO::new)
                .collect(Collectors.toList());
    }

}
