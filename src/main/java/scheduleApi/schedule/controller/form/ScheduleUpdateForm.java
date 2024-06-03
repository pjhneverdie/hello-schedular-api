package scheduleApi.schedule.controller.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ScheduleUpdateForm {
    @NotNull
    private int id;

    @NotNull
    private LocalDateTime dateTime;

    @Size(min = 1, max = 25, message = "제목은 1자 이상 25자 이하여야 합니다.")
    @NotBlank(message = "제목을 입력해 주세요.")
    private String title;

    private String memo;
}
