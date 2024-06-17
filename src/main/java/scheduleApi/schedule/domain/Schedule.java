package scheduleApi.schedule.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
public class Schedule {
    private final Integer id;

    private final LocalDateTime startTime;

    private final LocalDateTime endTime;

    private final String title;

    private final String memo;

    // save
    public Schedule(LocalDateTime startTime, LocalDateTime endTime, String title, String memo) {
        this(null, startTime, endTime, title, memo);
    }

    // update
    public Schedule(Integer id, LocalDateTime startTime, LocalDateTime endTime, String title, String memo) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.title = title;
        this.memo = memo;
    }
}


