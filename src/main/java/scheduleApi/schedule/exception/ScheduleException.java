package scheduleApi.schedule.exception;

import scheduleApi.common.exception.CustomException;
import scheduleApi.common.exception.ErrorCode;

public class ScheduleException extends CustomException {
    public ScheduleException(ErrorCode errorCode) {
        super(errorCode);
    }
}
