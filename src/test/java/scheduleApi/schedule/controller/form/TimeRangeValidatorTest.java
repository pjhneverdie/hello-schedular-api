package scheduleApi.schedule.controller.form;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import scheduleApi.schedule.controller.validation.group.save.SaveGroupSequence;
import scheduleApi.schedule.controller.validation.group.update.UpdateGroupSequence;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class TimeRangeValidatorTest {
    private Validator validator;

    @BeforeEach
    public void setUpValidator() {
        final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void validateIdOnlyWhenUpdateGroup() {
        final LocalDateTime startTime = LocalDateTime.now(Clock.system(ZoneId.of("Asia/Seoul")));
        final LocalDateTime endTime = startTime.plusHours(1);

        final ScheduleForm form = new ScheduleForm(
                null,
                startTime,
                endTime,
                "valid title",
                "valid memo"
        );

        /**
         * update 그룹
         * id가 null일 때 벨리데이션에 실패해야 함
         */
        final Set<ConstraintViolation<ScheduleForm>> violations = validator.validate(form, UpdateGroupSequence.class);
        assertFalse(violations.isEmpty());

        boolean idViolation = violations.stream().allMatch(violation -> violation.getMessage().equals("id를 입력해 주세요."));
        assertTrue(idViolation);


        /**
         * save 그룹
         * id가 null이어도 ok
         */
        final Set<ConstraintViolation<ScheduleForm>> violationsWhenSaveGroup = validator.validate(form, SaveGroupSequence.class);
        assertTrue(violationsWhenSaveGroup.isEmpty());
    }


    @Test
    public void validateScheduleRangeWhenAllGroup() {
        final LocalDateTime startTime = LocalDateTime.now(Clock.system(ZoneId.of("Asia/Seoul")));
        final LocalDateTime endTime = startTime.minusHours(1);

        final ScheduleForm form = new ScheduleForm(
                null,
                startTime,
                endTime,
                "valid title",
                "valid memo"
        );

        /**
         * save 그룹
         * 시작 시간이 종료 시간보다 늦으면 벨리데이션에 실패해야 함
         */
        final Set<ConstraintViolation<ScheduleForm>> violations = validator.validate(form, SaveGroupSequence.class);
        assertFalse(violations.isEmpty());

        boolean scheduleRangeViolation = violations.stream().allMatch(violation -> violation.getMessage().equals("연일 일정은 아직 지원하지 않습니다."));
        assertTrue(scheduleRangeViolation);

        /**
         * update 그룹
         * 시작 시간이 종료 시간보다 늦으면 벨리데이션에 실패해야 함
         */
        final Set<ConstraintViolation<ScheduleForm>> violationsWhenUpdateGroup = validator.validate(form, UpdateGroupSequence.class);
        assertFalse(violationsWhenUpdateGroup.isEmpty());

        boolean scheduleRangeViolationWhenUpdateGroup = violations.stream().allMatch(violation -> violation.getMessage().equals("연일 일정은 아직 지원하지 않습니다."));
        assertTrue(scheduleRangeViolationWhenUpdateGroup);
    }
}