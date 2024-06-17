package scheduleApi.schedule.repository;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import org.springframework.transaction.annotation.Transactional;
import scheduleApi.schedule.domain.Schedule;

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

    @Test
    void contextLoads() {
        assertNotNull(repository);
    }

    @Test
    void save() {
        // Given
        final Schedule schedule = getSchedule();

        // When
        final Schedule saved = repository.save(schedule);

        // Then
        assertThat(saved.getId()).isEqualTo(schedule.getId());
    }

    @Test
    void findByDate() {
        // Given
        final Schedule saved = repository.save(getSchedule());

        // When
        final List<Schedule> allByDate = repository.findByDate(saved.getStartTime().toLocalDate());

        // Then
        assertThat(allByDate).isNotEmpty();
    }

    @Test
    void update() {
        // Given
        final Schedule saved = repository.save(getSchedule());

        // When
        final LocalDateTime startTime = LocalDateTime.now(Clock.system(ZoneId.of("Asia/Seoul")));
        final LocalDateTime endTime = startTime.plusHours(1);

        final Schedule schedule = new Schedule(
                saved.getId(),
                startTime,
                endTime,
                "Title2",
                "Memo2");

        repository.update(schedule);

        // Then
        final List<Schedule> allByDate = repository.findByDate(schedule.getStartTime().toLocalDate());

        assertThat(allByDate.stream().allMatch(updated -> updated.getTitle().equals(schedule.getTitle()))).isTrue();
    }

    @Test
    void delete() {
        // Given
        final Schedule saved = repository.save(getSchedule());

        // When
        repository.delete(saved.getId());

        // Then
        final List<Schedule> allByDate = repository.findByDate(saved.getStartTime().toLocalDate());

        assertThat(allByDate.stream().noneMatch(schedule -> schedule.getId().equals(saved.getId()))).isTrue();
    }

    @Test
    void deleteByDate() {
        // Given
        final Schedule saved = repository.save(getSchedule());

        // When
        repository.deleteByDate(saved.getStartTime().toLocalDate());

        // Then
        final List<Schedule> allByDate = repository.findByDate(saved.getStartTime().toLocalDate());

        assertThat(allByDate).isEmpty();
    }

    private Schedule getSchedule() {
        final LocalDateTime startTime = LocalDateTime.now(Clock.system(ZoneId.of("Asia/Seoul")));
        final LocalDateTime endTime = startTime.plusHours(1);

        return new Schedule(startTime, endTime, "Title", "Memo");
    }
}
