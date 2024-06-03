package scheduleApi.schedule.controller.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleSaveForm {
    @NotNull
    private final LocalDateTime dateTime;

    @Size(min = 1, max = 25, message = "제목은 1자 이상 25자 이하여야 합니다.")
    @NotBlank(message = "제목을 입력해 주세요.")
    private final String title;

    private final String memo;

    public ScheduleSaveForm(LocalDateTime dateTime, String title, String memo) {
        this.dateTime = dateTime;
        this.title = title;
        this.memo = memo;
    }
}
