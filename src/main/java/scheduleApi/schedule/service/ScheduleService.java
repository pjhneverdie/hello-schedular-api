package scheduleApi.schedule.service;

import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import scheduleApi.schedule.controller.form.ScheduleForm;
import scheduleApi.schedule.domain.Schedule;
import scheduleApi.schedule.repository.ScheduleRepository;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    public Schedule save(ScheduleForm scheduleSaveForm) {
        final Schedule schedule = new Schedule(
                scheduleSaveForm.getStartTime(),
                scheduleSaveForm.getEndTime(),
                scheduleSaveForm.getTitle(),
                scheduleSaveForm.getMemo());

        return scheduleRepository.save(schedule);
    }

    public List<Schedule> findByDate(LocalDate date) {
        return scheduleRepository.findByDate(date);
    }

    public void update(ScheduleForm scheduleUpdateForm) {
        final Schedule schedule = new Schedule(
                scheduleUpdateForm.getId(),
                scheduleUpdateForm.getStartTime(),
                scheduleUpdateForm.getEndTime(),
                scheduleUpdateForm.getTitle(),
                scheduleUpdateForm.getMemo());

        scheduleRepository.update(schedule);
    }

    public void delete(int id) {
        scheduleRepository.delete(id);
    }

    public void deleteByDate(LocalDate date) {
        scheduleRepository.deleteByDate(date);
    }
}
