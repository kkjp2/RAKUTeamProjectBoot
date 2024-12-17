package rakuproject.raku.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public class CustomException extends RuntimeException {
    private HttpStatus httpStatus = null;
    private String message = null;

    public CustomException(ErrorCode errorCode) {
        this.httpStatus = httpStatus;
        this.message = message;
    }


    public CustomException(HttpStatus httpStatus, String s) {
    }
}
