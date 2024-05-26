package scheduleApi.schedule.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import scheduleApi.schedule.domain.Schedule;
import scheduleApi.schedule.repository.ScheduleRepository;

@Component
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    @Transactional
    public Schedule save(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }
}
