package scheduleApi.schedule.controller.form;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class TimeRangeValidatorTest {
    private Validator validator;

    @BeforeEach
    public void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void whenStartTimeIsGreaterThanEndTime_thenValidationFails() {
        final ScheduleSaveForm form = new ScheduleSaveForm(
                LocalDateTime.now(),
                1500,
                1300,
                "Valid Title",
                "Memo"
        );

        Set<ConstraintViolation<ScheduleSaveForm>> violations = validator.validate(form);

        assertFalse(violations.isEmpty());
        boolean validTimeRangeViolationFound = violations.stream()
                .anyMatch(violation -> violation.getMessage().equals("연일 일정은 아직 지원하지 않습니다."));
        assertTrue(validTimeRangeViolationFound);
    }
}