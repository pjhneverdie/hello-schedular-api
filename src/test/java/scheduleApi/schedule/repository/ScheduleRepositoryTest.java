package scheduleApi.schedule.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import scheduleApi.schedule.domain.Schedule;

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
        final Schedule saved = repository.save(schedule);

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

        final Schedule saved = repository.save(schedule);

        // When
        final List<Schedule> allByDate = repository.findByDate(saved.getDateTime().toLocalDate());

        // Then
        assertThat(allByDate).isNotEmpty();
    }

    @Test
    void update() {
        // Given
        Schedule schedule = new Schedule(
                LocalDateTime.now(Clock.system(ZoneId.of("Asia/Seoul"))),
                "title",
                "memo");

        final Schedule saved = repository.save(schedule);

        // When
        schedule = new Schedule(
                saved.getId(),
                LocalDateTime.now(Clock.system(ZoneId.of("Asia/Seoul"))),
                "updated title",
                "updated memo");

        repository.update(schedule);

        // Then
        List<Schedule> allByDate = repository.findByDate(schedule.getDateTime().toLocalDate());

        assertThat(allByDate.stream().findFirst().isPresent()).isTrue();
    }

    @Test
    void delete() {
        // Given
        final Schedule schedule = new Schedule(
                LocalDateTime.now(Clock.system(ZoneId.of("Asia/Seoul"))),
                "title",
                "memo");

        final Schedule saved = repository.save(schedule);

        // When
        repository.delete(saved.getId());

        // Then
        final List<Schedule> allByDate = repository.findByDate(saved.getDateTime().toLocalDate());

        assertThat(allByDate.contains(schedule)).isFalse();
    }

    @Test
    void deleteByDate() {
        // Given
        final Schedule schedule = new Schedule(
                LocalDateTime.now(Clock.system(ZoneId.of("Asia/Seoul"))),
                "title",
                "memo");

        final Schedule saved = repository.save(schedule);

        // When
        repository.deleteByDate(saved.getDateTime().toLocalDate());

        // Then
        final List<Schedule> allByDate = repository.findByDate(saved.getDateTime().toLocalDate());

        assertThat(allByDate).isEmpty();
    }
}
