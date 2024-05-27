package scheduleApi.schedule.exception;

import scheduleApi.schedule.common.exception.CustomException;
import scheduleApi.schedule.common.exception.ErrorCode;

public class ScheduleException extends CustomException {
    public ScheduleException(ErrorCode errorCode) {
        super(errorCode);
    }
}
