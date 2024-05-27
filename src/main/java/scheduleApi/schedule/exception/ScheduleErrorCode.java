package scheduleApi.schedule.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import scheduleApi.common.exception.ErrorCode;

@RequiredArgsConstructor
public enum ScheduleErrorCode implements ErrorCode {
    DATABASE_EXCEPTION("서버 오류 발생!", HttpStatus.INTERNAL_SERVER_ERROR);


    @Override
    public String message() {
        return message;
    }

    @Override
    public HttpStatus httpStatus() {
        return httpStatus;
    }

    @Override
    public RuntimeException exception() {
        return new ScheduleException(this);
    }

    @Override
    public RuntimeException exception(Throwable cause) {
        return new ScheduleException(this, cause);
    }

    private final String message;
    private final HttpStatus httpStatus;
}
