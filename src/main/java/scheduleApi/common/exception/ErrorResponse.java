package scheduleApi.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class ErrorResponse {
    private final int httpStatus;
    private final String message;

    public ErrorResponse(CustomException exception) {
        this.httpStatus = exception.errorCode.httpStatus().value();
        this.message = exception.errorCode.message();
    }
}
