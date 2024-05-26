package scheduleApi.schedule.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import scheduleApi.schedule.domain.Schedule;

import org.springframework.jdbc.core.JdbcTemplate;

@Component
@RequiredArgsConstructor
public class ScheduleRepository {

    private final JdbcTemplate template;

    public Schedule save(Schedule schedule) {
        String sql = "insert into schedule(id, dateTime, title, memo) values(?, ?, ?, ?)";
        template.update(sql, schedule.getId(), schedule.getDateTime(), schedule.getTitle(), schedule.getMemo());
        return schedule;
    }
}
