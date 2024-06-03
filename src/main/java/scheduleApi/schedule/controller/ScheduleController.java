package scheduleApi.schedule.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import scheduleApi.schedule.domain.Schedule;
import scheduleApi.schedule.controller.form.ScheduleSaveForm;
import scheduleApi.schedule.controller.form.ScheduleUpdateForm;
import scheduleApi.schedule.service.ScheduleService;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/schedule")
@RestController
public class ScheduleController {
    private final ScheduleService scheduleService;


    @PostMapping
    Schedule save(@Valid @RequestBody ScheduleSaveForm scheduleSaveForm) {
        return scheduleService.save(scheduleSaveForm);
    }

    @GetMapping("/{date}")
    List<Schedule> findByDate(@PathVariable LocalDate date) {
        return scheduleService.findByDate(date);
    }

    @PutMapping
    void update(@Valid @RequestBody ScheduleUpdateForm scheduleUpdateForm) {
        scheduleService.update(scheduleUpdateForm);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable Integer id) {
        scheduleService.delete(id);
    }

    @DeleteMapping("/{date}/all")
    void deleteByDate(@PathVariable LocalDate date) {
        scheduleService.deleteByDate(date);
    }
}
