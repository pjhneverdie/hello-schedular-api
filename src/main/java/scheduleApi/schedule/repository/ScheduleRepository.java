package scheduleApi.schedule.repository;

import lombok.RequiredArgsConstructor;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;

import scheduleApi.schedule.domain.Schedule;
import scheduleApi.schedule.domain.ScheduleDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;


@Repository
@RequiredArgsConstructor
public class ScheduleRepository {

    private final JdbcTemplate template;

    public ScheduleDto save(Schedule schedule) {
        final String sql = "insert into schedule(dateTime, title, memo) values(?, ?, ?)";

        final KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                final PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
                ps.setTimestamp(1, Timestamp.valueOf(schedule.getDateTime()));
                ps.setString(2, schedule.getTitle());
                ps.setString(3, schedule.getMemo());
                return ps;
            }
        }, keyHolder);

        return new ScheduleDto(
                keyHolder.getKey().intValue(),
                schedule.getDateTime(),
                schedule.getTitle(),
                schedule.getMemo()
        );
    }

    public ScheduleDto read(int id) {
        final String sql = "select * from schedule where id = ?";
        final ScheduleDto scheduleDto = template.queryForObject(sql, scheduleRowMapper(), id);
        return scheduleDto;
    }

    private RowMapper<ScheduleDto> scheduleRowMapper() {
        return (rs, rowNum) -> {
            ScheduleDto scheduleDto = new ScheduleDto(
                    rs.getInt("id"),
                    rs.getTimestamp("dateTime").toLocalDateTime(),
                    rs.getString("title"),
                    rs.getString("memo")
            );

            return scheduleDto;
        };
    }
}
