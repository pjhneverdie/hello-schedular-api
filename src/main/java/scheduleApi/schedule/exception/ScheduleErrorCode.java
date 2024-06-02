package scheduleApi.schedule.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import scheduleApi.schedule.common.exception.CustomException;
import scheduleApi.schedule.common.exception.ErrorCode;

@RequiredArgsConstructor
public enum ScheduleErrorCode implements ErrorCode {
    SCHEDULE_ERROR_CODE_NOT_FOUND("", HttpStatus.INTERNAL_SERVER_ERROR);

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

    private final String message;
    private final HttpStatus httpStatus;
}
