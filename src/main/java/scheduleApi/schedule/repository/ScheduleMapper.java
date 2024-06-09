package scheduleApi.schedule.repository;

import org.apache.ibatis.annotations.Mapper;

import scheduleApi.schedule.domain.Schedule;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface ScheduleMapper {

    void save(Schedule schedule);

    List<Schedule> findByDate(LocalDate date);

    void update(Schedule schedule);

    void delete(int id);

    void deleteByDate(LocalDate date);
}
