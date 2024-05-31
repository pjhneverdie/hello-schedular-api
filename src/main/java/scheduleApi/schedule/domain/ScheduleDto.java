package scheduleApi.schedule.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

// DB에 Auto Increment 걸어놔서 id를 명시할 필요가 없는데
// Read 작업 때문에 id 필드가 있어야 할 듯
// 그런데 Schedule 엔티티만 있으면 실수로 id를 집어넣을 수 있으니까 DTO를 만들어서 해결
@Getter
@RequiredArgsConstructor
public class ScheduleDto {
    @NotNull
    private final int id;

    @NotNull
    private final LocalDateTime dateTime;

    @Size(min = 1, max = 25, message = "제목은 1자 이상 25자 이하여야 합니다.")
    @NotBlank(message = "제목을 입력해 주세요.")
    private final String title;

    private final String memo;

    public Schedule toEntity() {
        return new Schedule(this.dateTime, this.title, this.memo);
    }
}
