package scheduleApi.schedule.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@Getter
public class Schedule {
    private final LocalDateTime dateTime;

    private final String title;

    private final String memo;

    @JsonCreator
    public Schedule(
            @JsonProperty("dateTime") LocalDateTime dateTime,
            @JsonProperty("title") String title,
            @JsonProperty("memo") String memo) {
        this.dateTime = dateTime;
        this.title = title;
        this.memo = memo;
    }
}


