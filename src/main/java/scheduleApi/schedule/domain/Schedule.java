package scheduleApi.schedule.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
public class Schedule {
    // save 시 auto_increment
    // read 시 NOT NULL
    private Integer id;

    private final LocalDateTime dateTime;

    private final String title;

    private final String memo;

    // save
    public Schedule(LocalDateTime dateTime, String title, String memo) {
        this.dateTime = dateTime;
        this.title = title;
        this.memo = memo;
    }

    // read
    public Schedule(int id, LocalDateTime dateTime, String title, String memo) {
        this.id = id;
        this.dateTime = dateTime;
        this.title = title;
        this.memo = memo;
    }
}


