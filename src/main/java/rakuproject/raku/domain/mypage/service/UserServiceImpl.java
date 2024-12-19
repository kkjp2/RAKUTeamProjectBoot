package rakuproject.raku.domain.mypage.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rakuproject.raku.domain.mypage.repository.UserRepository;
import rakuproject.raku.global.jwt.JwtUtil;

@Service
@RequiredArgsConstructor
public class UserServiceImpl {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    public Long getUserKeyFromToken(String token) {

        String userId = jwtUtil.getUserIdFromToken(token);

        System.out.println(userId);

        return  userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"))
                .getUserKey();

    }
}
