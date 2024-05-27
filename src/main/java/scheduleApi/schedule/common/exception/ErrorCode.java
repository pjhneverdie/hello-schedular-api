package scheduleApi.schedule.common.exception;

import org.springframework.http.HttpStatus;

public interface ErrorCode {
    String message();

    HttpStatus httpStatus();

    RuntimeException exception();
}
