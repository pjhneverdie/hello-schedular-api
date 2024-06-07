package scheduleApi.schedule.common.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class MinMaxValidator implements ConstraintValidator<MinMax, Integer> {

    private int min;
    private int max;

    @Override
    public void initialize(MinMax constraintAnnotation) {
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        return value >= min && value <= max;
    }
}