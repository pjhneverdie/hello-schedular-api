package scheduleApi.schedule.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import scheduleApi.schedule.domain.Schedule;
import scheduleApi.schedule.repository.dto.ScheduleUpdateDto;

import javax.sql.DataSource;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Transactional
@SpringBootTest
class ScheduleRepositoryTest {

    @Autowired
    private ScheduleRepository repository;

    @TestConfiguration
    public static class TestConfig {

        @Bean
        public ScheduleRepository scheduleRepository(DataSource dataSource) {
            return new ScheduleRepository(new NamedParameterJdbcTemplate(dataSource));
        }
    }

    @Test
    void contextLoads() {
        assertNotNull(repository);
    }

    @Test
    void save() {
        // Given
        final Schedule schedule = new Schedule(
                LocalDateTime.now(Clock.system(ZoneId.of("Asia/Seoul"))),
                "title",
                "memo");

        // When
        final ScheduleUpdateDto saved = repository.save(schedule);

        // Then
        assertThat(saved.getDateTime()).isEqualTo(schedule.getDateTime());
        assertThat(saved.getTitle()).isEqualTo(schedule.getTitle());
        assertThat(saved.getMemo()).isEqualTo(schedule.getMemo());
    }

    @Test
    void findByDate() {
        // Given
        final Schedule schedule = new Schedule(
                LocalDateTime.now(Clock.system(ZoneId.of("Asia/Seoul"))),
                "title",
                "memo");

        final ScheduleUpdateDto saved = repository.save(schedule);

        // When
        final List<ScheduleUpdateDto> allByDate = repository.findByDate(saved.getDateTime().toLocalDate());

        // Then
        assertThat(allByDate).isNotEmpty();
    }

    @Test
    void update() {
        // Given
        final Schedule schedule = new Schedule(
                LocalDateTime.now(Clock.system(ZoneId.of("Asia/Seoul"))),
                "title",
                "memo");

        final ScheduleUpdateDto saved = repository.save(schedule);
        saved.setDateTime(LocalDateTime.now(Clock.system(ZoneId.of("Asia/Seoul"))));
        saved.setTitle("title2");
        saved.setMemo("memo2");

        // When
        ScheduleUpdateDto updated = repository.update(saved);

        // Then
        assertThat(updated.getId()).isEqualTo(saved.getId());
    }

    @Test
    void delete() {
        // Given
        final Schedule schedule = new Schedule(
                LocalDateTime.now(Clock.system(ZoneId.of("Asia/Seoul"))),
                "title",
                "memo");

        final ScheduleUpdateDto saved = repository.save(schedule);

        // When
        repository.delete(saved.getId());

        final List<ScheduleUpdateDto> allByDate = repository.findByDate(saved.getDateTime().toLocalDate());

        // Then
        assertThat(allByDate).isEmpty();
    }

    @Test
    void deleteByDate() {
        // Given
        final Schedule schedule = new Schedule(
                LocalDateTime.now(Clock.system(ZoneId.of("Asia/Seoul"))),
                "title",
                "memo");

        final ScheduleUpdateDto saved = repository.save(schedule);

        // When
        repository.deleteByDate(saved.getDateTime().toLocalDate());

        final List<ScheduleUpdateDto> allByDate = repository.findByDate(saved.getDateTime().toLocalDate());

        // Then
        assertThat(allByDate).isEmpty();
    }
}
