package scheduleApi.schedule.controller.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import scheduleApi.schedule.controller.form.TimeValidatedScheduleForm;

public class TimeRangeValidator implements ConstraintValidator<TimeRangeConstraint, TimeValidatedScheduleForm> {

    @Override
    public void initialize(TimeRangeConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(TimeValidatedScheduleForm timeValidatedScheduleForm, ConstraintValidatorContext constraintValidatorContext) {
        return timeValidatedScheduleForm != null &&
                timeValidatedScheduleForm.startTime() < timeValidatedScheduleForm.endTime();
    }
}