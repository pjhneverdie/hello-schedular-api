package scheduleApi.schedule.common.controller;

import org.springframework.web.bind.annotation.*;

import scheduleApi.schedule.common.exception.CustomException;
import scheduleApi.schedule.common.exception.ErrorResponse;

@RestControllerAdvice
public class ApiExceptionController {

    @ExceptionHandler(CustomException.class)
    public ErrorResponse handleCustomException(CustomException e) {
        return new ErrorResponse(e);
    }
}
