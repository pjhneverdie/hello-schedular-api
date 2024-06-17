package scheduleApi.schedule.controller.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import scheduleApi.schedule.controller.form.ScheduleForm;

public class TimeRangeValidator implements ConstraintValidator<TimeRangeConstraint, ScheduleForm> {

    @Override
    public void initialize(TimeRangeConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(ScheduleForm scheduleForm, ConstraintValidatorContext constraintValidatorContext) {
        return scheduleForm.getEndTime().isAfter(scheduleForm.getStartTime());
    }
}