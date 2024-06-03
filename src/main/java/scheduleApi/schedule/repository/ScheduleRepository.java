package scheduleApi.schedule.repository;

import lombok.RequiredArgsConstructor;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import scheduleApi.schedule.domain.Schedule;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;


@Repository
@RequiredArgsConstructor
public class ScheduleRepository {

    private final NamedParameterJdbcTemplate template;
    private SimpleJdbcInsert simpleJdbcInsert;

    @EventListener(ApplicationReadyEvent.class)
    public void initData() {
        this.simpleJdbcInsert = new SimpleJdbcInsert(template.getJdbcTemplate())
                .withTableName("schedule")
                .usingGeneratedKeyColumns("id");
    }


    public Schedule save(Schedule schedule) {
        final BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(schedule);
        final Number id = simpleJdbcInsert.executeAndReturnKey(param);

        return new Schedule(id.intValue(), schedule.getDateTime(), schedule.getTitle(), schedule.getMemo());
    }

    public List<Schedule> findByDate(LocalDate date) {
        final String sql = "SELECT * FROM schedule WHERE DATE(date_time) = :date";

        final Map<String, LocalDate> param = Map.of("date", date);

        return template.query(sql, param, scheduleRowMapper());
    }


    public void update(Schedule schedule) {
        final String sql = "UPDATE schedule " +
                "SET date_time = :dateTime, title = :title, memo = :memo " +
                "WHERE id = :id";

        final BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(schedule);

        template.update(sql, param);
    }

    public void delete(int id) {
        final String sql = "DELETE FROM schedule " +
                "WHERE id = :id";

        final Map<String, Integer> param = Map.of("id", id);

        template.update(sql, param);
    }

    public void deleteByDate(LocalDate date) {
        final String sql = "DELETE FROM schedule " +
                "WHERE DATE(date_time) = :date";

        final Map<String, LocalDate> param = Map.of("date", date);

        template.update(sql, param);
    }

    private RowMapper<Schedule> scheduleRowMapper() {
        return ((rs, rowNum) -> {
            final Schedule schedule = new Schedule(
                    rs.getInt("id"),
                    rs.getTimestamp("date_time").toLocalDateTime(),
                    rs.getString("title"),
                    rs.getString("memo"));

            return schedule;
        });
    }
}
