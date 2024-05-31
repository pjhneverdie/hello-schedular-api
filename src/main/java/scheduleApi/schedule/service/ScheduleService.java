package scheduleApi.schedule.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import scheduleApi.schedule.domain.Schedule;
import scheduleApi.schedule.domain.ScheduleCreateRequestDto;
import scheduleApi.schedule.domain.ScheduleDto;
import scheduleApi.schedule.repository.ScheduleRepository;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    @Transactional
    public ScheduleDto save(ScheduleCreateRequestDto scheduleCreateRequestDto) {
        return scheduleRepository.save(scheduleCreateRequestDto.toEntity());
    }

    @Transactional
    public ScheduleDto read(int id) {
        return scheduleRepository.read(id);
    }
}
