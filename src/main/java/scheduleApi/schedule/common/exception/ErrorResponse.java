package scheduleApi.schedule.common.exception;

import lombok.Getter;

@Getter
public class ErrorResponse {
    private final int httpStatus;
    private final String message;


    public ErrorResponse(CustomException exception) {
        this.httpStatus = exception.getErrorCode().httpStatus().value();
        this.message = exception.getErrorCode().message();
    }
}
