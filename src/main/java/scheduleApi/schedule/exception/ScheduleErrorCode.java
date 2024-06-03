package scheduleApi.schedule.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import scheduleApi.schedule.common.exception.CustomException;
import scheduleApi.schedule.common.exception.ErrorCode;

@RequiredArgsConstructor
public enum ScheduleErrorCode implements ErrorCode {
    SOME_SCHEDULE_ERROR("스케쥴을 저장할 수 없습니다 ㅠ", HttpStatus.INTERNAL_SERVER_ERROR);

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

    CustomException toCustomException() {
        return new CustomException(this);
    }

    private final String message;
    private final HttpStatus httpStatus;
}
