package com.example.board_test.board.service;


import com.example.board_test.board.dto.request.HashTagRequestDTO;
import com.example.board_test.board.dto.response.HashTagResponseDTO;
import com.example.board_test.board.entity.HashTagEntity;
import com.example.board_test.board.repository.HashtagRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class HashTagService {
    @Autowired
    private final HashtagRepository hashtagRepository;

    @Transactional
    public void searchKeyword(HashTagRequestDTO hashTagRequestDTO)
    {
        String keyword=hashTagRequestDTO.getKeyword();
        if(hashtagRepository.existsByKeyword(keyword))
        {
            hashtagRepository.SearchCount(keyword);
        }
        else{
            HashTagEntity newHashTag= hashTagRequestDTO.toEntity();
            hashtagRepository.save(newHashTag);
        }
    }

    @Transactional(readOnly = true)
    public List<HashTagResponseDTO> getTop10Hashtags()
    {
//        return hashtagRepository.findTop10ByOrderBySearchCountDesc()
//                .stream()
//                .map(HashTagResponseDTO::new)
//                .collect(Collectors.toList());
        return null;
    }

}
