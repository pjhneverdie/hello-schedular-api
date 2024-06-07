package scheduleApi.schedule.controller.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import scheduleApi.schedule.controller.form.ScheduleSaveForm;

public class TimeRangeValidator implements ConstraintValidator<TimeRangeConstraint, ScheduleSaveForm> {

    @Override
    public boolean isValid(ScheduleSaveForm form, ConstraintValidatorContext context) {
        return form != null && form.getStartTime() < form.getEndTime();
    }
}