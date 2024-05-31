package scheduleApi.schedule.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

// DTO는 Setter 써도 괜찮을 듯 어차피 데이터 전달 목적이니까
@Getter
@Setter
public class ScheduleCreateRequestDto {
    @NotNull
    private LocalDateTime dateTime;

    @Size(min = 1, max = 25, message = "제목은 1자 이상 25자 이하여야 합니다.")
    @NotBlank(message = "제목을 입력해 주세요.")
    private String title;

    private String memo;

    public Schedule toEntity() {
        return new Schedule(this.dateTime, this.title, this.memo);
    }
}
