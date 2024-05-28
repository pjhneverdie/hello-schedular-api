package scheduleApi.schedule.domain;

import jakarta.validation.constraints.*;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
public class Schedule {
    @NotNull
    private LocalDateTime dateTime;

    @Size(min = 1, max = 25, message = "제목은 1자 이상 25자 이하여야 합니다.")
    @NotBlank(message = "제목을 입력해 주세요.")
    private String title;

    private String memo;
}


