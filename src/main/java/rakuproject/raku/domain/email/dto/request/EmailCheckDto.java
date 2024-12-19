package rakuproject.raku.domain.email.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class EmailCheckDto {
    @Email
    @NotEmpty(message = "이메일을 입력해 주세요")
    private String id;

    @NotEmpty(message = "인증 번호를 입력해 주세요")
    private String authNum;
}
