//package com.example.board_test.domain.mypage.controller;
//
//import com.example.board_test.domain.mypage.service.MyPageService;
//import com.example.board_test.global.jwt.JwtUtil;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api/mypage")
//@RequiredArgsConstructor
//public class MyPageController {
//    @Autowired
//    private JwtUtil jwtUtil;
//
//    private MyPageService myPageService;

//    @GetMapping("")
//    public String getUserDetails(@RequestHeader("Authorization") String token) {
//        String userId = jwtUtil.getUserIdFromToken(token.substring(7)); // "Bearer " 제거
//        return ResponseEntity.ok(boardDTO);
//    }
//}
