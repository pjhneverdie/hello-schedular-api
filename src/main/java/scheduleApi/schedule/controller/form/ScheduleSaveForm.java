package scheduleApi.schedule.controller.form;

import jakarta.validation.constraints.*;

import lombok.Getter;
import scheduleApi.schedule.common.validation.MinMax;
import scheduleApi.schedule.controller.validation.TimeRangeConstraint;
import scheduleApi.schedule.controller.validation.group.ValidationGroups;

import java.time.LocalDateTime;

@TimeRangeConstraint(groups = ValidationGroups.ValueRangeGroup.class)
@Getter
public class ScheduleSaveForm implements TimeValidatedScheduleForm {
    @NotNull(message = "날짜를 입력해 주세요.", groups = ValidationGroups.NotNullGroup.class)
    private final LocalDateTime dateTime;

    @NotNull(message = "일정 시작 시간을 입력해 주세요.", groups = ValidationGroups.NotNullGroup.class)
    @MinMax(min = 0, max = 1430, message = "일정 시작 시간은 00시 00분 ~ 23시 50분까지로 제한됩니다.",
            groups = ValidationGroups.ValueRangeGroup.class)
    private final int startTime;

    @NotNull(message = "일정 마무리 시간을 입력해 주세요.", groups = ValidationGroups.NotNullGroup.class)
    @MinMax(min = 10, max = 1440, message = "일정 마무리 시간은 00시 10분 ~ 24시 00분까지로 제한됩니다.",
            groups = ValidationGroups.ValueRangeGroup.class)
    private final int endTime;

    @NotBlank(message = "제목을 입력해 주세요.", groups = ValidationGroups.NotBlankGroup.class)
    @Size(min = 1, max = 25, message = "제목은 1자 이상 25자 이하여야 합니다.",
            groups = ValidationGroups.ValueRangeGroup.class)
    private final String title;

    private final String memo;

    public ScheduleSaveForm(LocalDateTime dateTime, int startTime, int endTime, String title, String memo) {
        this.dateTime = dateTime;
        this.startTime = startTime;
        this.endTime = endTime;
        this.title = title;
        this.memo = memo;
    }

    @Override
    public int startTime() {
        return this.startTime;
    }

    @Override
    public int endTime() {
        return this.endTime;
    }
}
