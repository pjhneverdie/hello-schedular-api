package scheduleApi.schedule.repository;

import org.assertj.core.api.Assertions;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

import scheduleApi.schedule.domain.Schedule;
import scheduleApi.schedule.domain.ScheduleCreateRequestDto;
import scheduleApi.schedule.domain.ScheduleDto;
import scheduleApi.schedule.service.ScheduleService;

import java.time.LocalDateTime;

@SpringBootTest
class ScheduleRepositoryTest {

    @Autowired
    private ScheduleService service;

    @TestConfiguration
    private static class TestConfig {

        @Bean
        ScheduleRepository scheduleRepository(JdbcTemplate jdbcTemplate) {
            return new ScheduleRepository(jdbcTemplate);
        }

        @Bean
        ScheduleService scheduleService(ScheduleRepository scheduleRepository) {
            return new ScheduleService(scheduleRepository);
        }
    }

    @Test
    void crud() {
        final ScheduleCreateRequestDto scheduleCreateRequestDto = new ScheduleCreateRequestDto();
        scheduleCreateRequestDto.setDateTime(LocalDateTime.now());
        scheduleCreateRequestDto.setTitle("title");
        scheduleCreateRequestDto.setMemo("memo");

        final ScheduleDto saved = service.save(scheduleCreateRequestDto);

        final ScheduleDto read = service.read(saved.getId());
        Assertions.assertThat(saved.getId()).isEqualTo(read.getId());
    }
}