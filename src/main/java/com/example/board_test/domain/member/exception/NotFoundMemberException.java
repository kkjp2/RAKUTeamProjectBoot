package com.example.board_test.domain.member.exception;

import com.example.board_test.global.exception.CustomException;
import org.springframework.http.HttpStatus;


public class NotFoundMemberException extends CustomException {
    public static final CustomException EXCEPTION =
            new NotFoundMemberException();
    public NotFoundMemberException() {
        super(HttpStatus.NOT_FOUND, "회원을 찾을 수 없습니다.");
    }
}
