package scheduleApi.schedule.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import scheduleApi.schedule.domain.Schedule;

import org.springframework.jdbc.core.JdbcTemplate;
import scheduleApi.schedule.exception.ScheduleErrorCode;

@Component
@RequiredArgsConstructor
public class ScheduleRepository {

    private final JdbcTemplate template;

    public Schedule save(Schedule schedule) {
        try {
            String sql = "insert into schedule(id, dateTdime, title, memo) values(?, ?, ?, ?)";
            template.update(sql, schedule.getId(), schedule.getDateTime(), schedule.getTitle(), schedule.getMemo());
            return schedule;
        } catch (DataAccessException e) {
            throw ScheduleErrorCode.DATABASE_EXCEPTION.toException();
        }
    }
}
