package rakuproject.raku.domain.email.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rakuproject.raku.domain.email.dto.request.EmailCheckDto;
import rakuproject.raku.domain.email.dto.request.EmailRequestDto;
import rakuproject.raku.domain.email.dto.response.EmailResponseDto;
import rakuproject.raku.domain.email.service.MailSendService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class MailController {
    private final MailSendService mailService;

    @PostMapping("/mailsend")
    public ResponseEntity<EmailResponseDto> mailSend(@RequestBody @Valid EmailRequestDto emailDto) {
        System.out.println("이메일 인증 이메일 :" + emailDto.getId());
        return ResponseEntity.ok(mailService.joinEmail(emailDto.getId()));
    }

    @PostMapping("/mailauthcheck")
    public String AuthCheck(@RequestBody @Valid EmailCheckDto emailCheckDto){
        Boolean Checked=mailService.CheckAuthNum(emailCheckDto.getId(),emailCheckDto.getAuthNum());
        if(Checked){
            return "ok";
        }
        else{
            throw new NullPointerException("뭔가 잘못!");
        }
    }
}
