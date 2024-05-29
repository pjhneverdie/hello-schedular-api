package scheduleApi.schedule.repository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;

import scheduleApi.schedule.domain.Schedule;


@Repository
@RequiredArgsConstructor
public class ScheduleRepository {

    private final JdbcTemplate template;

    public Schedule save(Schedule schedule) {
        String sql = "insert into schedule(dateTime, title, memo) values(?, ?, ?)";
        template.update(sql, schedule.getDateTime(), schedule.getTitle(), schedule.getMemo());
        return schedule;
    }
}
