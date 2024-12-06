package rakuproject.raku.domain.mypage.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rakuproject.raku.domain.mypage.dto.response.MypageMainResponse;
import rakuproject.raku.domain.mypage.service.MypageServiceImpl;
import rakuproject.raku.domain.mypage.service.UserServiceImpl;

@RestController
@RequestMapping("/api/mypage")
@RequiredArgsConstructor
public class MypageController {

    @Autowired
    private MypageServiceImpl mypageService;

    @Autowired
    private UserServiceImpl userService;


    @GetMapping
    public MypageMainResponse getUserInfo(@RequestHeader("Authorization") String authorizationHeader){

        String token = authorizationHeader.substring(7);

        Long userkey = userService.getUserKeyFromToken(token);

        System.out.println(userkey);

        return mypageService.getUserInfo(userkey);
    }
}
