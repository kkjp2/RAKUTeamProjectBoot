package rakuproject.raku.domain.move.exception;
//에러 처리（Exception）
public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
