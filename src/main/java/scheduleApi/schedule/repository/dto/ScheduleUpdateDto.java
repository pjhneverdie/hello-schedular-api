package scheduleApi.schedule.repository.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import scheduleApi.schedule.domain.Schedule;

import java.time.LocalDateTime;


@Getter
@Setter
public class ScheduleUpdateDto {
    @NotNull
    private int id;

    @NotNull
    private LocalDateTime dateTime;

    @Size(min = 1, max = 25, message = "제목은 1자 이상 25자 이하여야 합니다.")
    @NotBlank(message = "제목을 입력해 주세요.")
    private String title;

    private String memo;

    public Schedule toEntity() {
        return new Schedule(this.dateTime, this.title, this.memo);
    }

    @Override
    public String toString() {
        return "ScheduleUpdateDto{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", title='" + title + '\'' +
                ", memo='" + memo + '\'' +
                '}';
    }
}
