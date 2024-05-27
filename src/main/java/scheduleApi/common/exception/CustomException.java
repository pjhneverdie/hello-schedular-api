package scheduleApi.common.exception;


public class CustomException extends RuntimeException {
    public final ErrorCode errorCode;

    public CustomException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
