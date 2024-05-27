package scheduleApi.common.exception;

import org.springframework.http.HttpStatus;

public interface ErrorCode {
    String message();

    HttpStatus httpStatus();

    RuntimeException exception();

    RuntimeException exception(Throwable cause);
}
