package scheduleApi.schedule.repository;

import lombok.RequiredArgsConstructor;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import scheduleApi.schedule.domain.Schedule;
import scheduleApi.schedule.repository.dto.ScheduleUpdateDto;

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


    public ScheduleUpdateDto save(Schedule schedule) {
        final BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(schedule);
        final Number id = simpleJdbcInsert.executeAndReturnKey(param);

        final ScheduleUpdateDto scheduleUpdateDto = new ScheduleUpdateDto();
        scheduleUpdateDto.setId(id.intValue());
        scheduleUpdateDto.setDateTime(schedule.getDateTime());
        scheduleUpdateDto.setTitle(schedule.getTitle());
        scheduleUpdateDto.setMemo(schedule.getMemo());

        return scheduleUpdateDto;
    }

    public List<ScheduleUpdateDto> findByDate(LocalDate date) {
        final String sql = "SELECT * FROM schedule WHERE DATE(date_time) = :date";

        final Map<String, LocalDate> param = Map.of("date", date);

        return template.query(sql, param, scheduleRowMapper());
    }


    public ScheduleUpdateDto update(ScheduleUpdateDto scheduleUpdateDto) {
        final String sql = "UPDATE schedule " +
                "SET date_time = :dateTime, title = :title, memo = :memo " +
                "WHERE id = :id";

        final BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(scheduleUpdateDto);

        template.update(sql, param);

        return scheduleUpdateDto;
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

    private RowMapper<ScheduleUpdateDto> scheduleRowMapper() {
        return new BeanPropertyRowMapper<>(ScheduleUpdateDto.class);
    }
}
