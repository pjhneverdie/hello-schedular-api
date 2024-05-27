package scheduleApi.schedule.exception;

import org.springframework.dao.DataAccessException;

import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;
import scheduleApi.common.exception.ErrorResponse;

@RestControllerAdvice
public class ApiExceptionController {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(DataAccessException.class)
    public ErrorResponse handleDatabaseException(DataAccessException e) {
        return new ErrorResponse(new ScheduleException(ScheduleErrorCode.DATABASE_EXCEPTION));
    }
}
