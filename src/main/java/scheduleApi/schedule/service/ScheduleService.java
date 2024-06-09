package scheduleApi.schedule.service;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import scheduleApi.schedule.domain.Schedule;
import scheduleApi.schedule.controller.form.ScheduleSaveForm;
import scheduleApi.schedule.controller.form.ScheduleUpdateForm;
import scheduleApi.schedule.repository.ScheduleRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    public Schedule save(ScheduleSaveForm scheduleSaveForm) {
        final Schedule schedule = new Schedule(
                scheduleSaveForm.getDateTime(),
                scheduleSaveForm.getStartTime(),
                scheduleSaveForm.getEndTime(),
                scheduleSaveForm.getTitle(),
                scheduleSaveForm.getMemo());

        return scheduleRepository.save(schedule);
    }

    public List<Schedule> findByDate(LocalDate date) {
        return scheduleRepository.findByDate(date);
    }

    public void update(ScheduleUpdateForm scheduleUpdateForm) {
        final Schedule schedule = new Schedule(
                scheduleUpdateForm.getId(),
                scheduleUpdateForm.getDateTime(),
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
