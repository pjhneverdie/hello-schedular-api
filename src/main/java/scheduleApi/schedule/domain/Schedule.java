package scheduleApi.schedule.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
public class Schedule {
    private Integer id;

    private final LocalDateTime dateTime;

    private final int startTime;

    private final int endTime;

    private final String title;

    private final String memo;

    // save
    public Schedule(LocalDateTime dateTime, int startTime, int endTime, String title, String memo) {
        this.dateTime = dateTime;
        this.startTime = startTime;
        this.endTime = endTime;
        this.title = title;
        this.memo = memo;
    }

    // read
    public Schedule(int id, LocalDateTime dateTime, int startTime, int endTime, String title, String memo) {
        this.id = id;
        this.dateTime = dateTime;
        this.startTime = startTime;
        this.endTime = endTime;
        this.title = title;
        this.memo = memo;
    }
}


