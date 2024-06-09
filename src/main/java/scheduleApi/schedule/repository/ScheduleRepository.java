package scheduleApi.schedule.repository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Repository;

import scheduleApi.schedule.domain.Schedule;

import java.time.LocalDate;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ScheduleRepository {

    private final ScheduleMapper scheduleMapper;

    public Schedule save(Schedule schedule) {
        scheduleMapper.save(schedule);
        return schedule;
    }

    public List<Schedule> findByDate(LocalDate date) {
        return scheduleMapper.findByDate(date);
    }

    public void update(Schedule schedule) {
        scheduleMapper.update(schedule);
    }

    public void delete(int id) {
        scheduleMapper.delete(id);
    }

    public void deleteByDate(LocalDate date) {
        scheduleMapper.deleteByDate(date);
    }
}
