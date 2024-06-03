package scheduleApi.schedule.service;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import scheduleApi.schedule.repository.dto.ScheduleSaveDto;
import scheduleApi.schedule.repository.dto.ScheduleUpdateDto;
import scheduleApi.schedule.repository.ScheduleRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    public ScheduleUpdateDto save(ScheduleSaveDto scheduleSaveDto) {
        return scheduleRepository.save(scheduleSaveDto.toEntity());
    }

    public List<ScheduleUpdateDto> findByDate(LocalDate date) {
        return scheduleRepository.findByDate(date);
    }

    public ScheduleUpdateDto update(ScheduleUpdateDto scheduleUpdateDto) {
        return scheduleRepository.update(scheduleUpdateDto);
    }

    public void delete(int id) {
        scheduleRepository.delete(id);
    }

    public void deleteByDate(LocalDate date) {
        scheduleRepository.deleteByDate(date);
    }
}
