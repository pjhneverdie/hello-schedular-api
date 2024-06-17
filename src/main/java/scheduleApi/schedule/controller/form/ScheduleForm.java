package scheduleApi.schedule.controller.form;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import scheduleApi.schedule.controller.validation.TimeRangeConstraint;
import scheduleApi.schedule.controller.validation.group.update.UpdateGroup;
import scheduleApi.schedule.controller.validation.group.save.SaveGroup;

@TimeRangeConstraint(groups = SaveGroup.TimeRangeGroup.class)
@Getter
public class ScheduleForm {
    @NotNull(message = "id를 입력해 주세요.", groups = UpdateGroup.class)
    private final Integer id;

    @NotNull(message = "일정 시작 날짜를 입력해 주세요.", groups = SaveGroup.NotNullGroup.class)
    private final LocalDateTime startTime;

    @NotNull(message = "일정 종료 날짜를 입력해 주세요.", groups = SaveGroup.NotNullGroup.class)
    private final LocalDateTime endTime;

    @NotBlank(message = "제목을 입력해 주세요.", groups = SaveGroup.NotBlankGroup.class)
    @Size(min = 1, max = 25, message = "제목은 1자 이상 25자 이하여야 합니다.",
            groups = SaveGroup.SizeGroup.class)
    private final String title;

    private final String memo;

    @JsonCreator
    public ScheduleForm(@JsonProperty("id") Integer id,
                        @JsonProperty("startTime") LocalDateTime startTime,
                        @JsonProperty("endTime") LocalDateTime endTime,
                        @JsonProperty("title") String title,
                        @JsonProperty("memo") String memo) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.title = title;
        this.memo = memo;
    }
}