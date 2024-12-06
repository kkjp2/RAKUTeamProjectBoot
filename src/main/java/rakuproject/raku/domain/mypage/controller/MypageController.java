//package rakuproject.raku.domain.mypage.controller;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestHeader;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import rakuproject.raku.domain.mypage.service.MypageService;
//import rakuproject.raku.global.jwt.JwtUtil;
//
//@RestController
//@RequestMapping("/api/mypage")
//@RequiredArgsConstructor
//public class MypageController {
//
//    @Autowired
//    private JwtUtil jwtUtil;
//    private final MypageService mypageService;
//
//    @GetMapping("")
//    public String getUserDetails(@RequestHeader("Authorization") String token) {
//        String userId = jwtUtil.getUserIdFromToken(token.substring(7)); // "Bearer " 제거
//        return ResponseEntity.ok(boardDTO);
//    }
//
//}
