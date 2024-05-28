package scheduleApi.schedule.common.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;

import org.springframework.http.HttpStatus;

@Getter
public class ErrorResponse {
    private final int httpStatus;
    private final String message;

    public ErrorResponse(CustomException exception) {
        this.httpStatus = exception.getErrorCode().httpStatus().value();
        this.message = exception.getErrorCode().message();
    }

    @JsonIgnore
    public HttpStatus getEnumHttpStatus() {
        return HttpStatus.valueOf(this.httpStatus);
    }
}
