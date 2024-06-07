package scheduleApi.schedule.controller.validation;

import jakarta.validation.Constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = TimeRangeValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface TimeRangeConstraint {
    String message() default "연일 일정은 아직 지원하지 않습니다.";

    Class<?>[] groups() default {};

    Class[] payload() default {};
}