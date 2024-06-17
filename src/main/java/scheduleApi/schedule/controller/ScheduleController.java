package scheduleApi.schedule.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import scheduleApi.schedule.controller.form.ScheduleForm;
import scheduleApi.schedule.controller.validation.group.save.SaveGroupSequence;
import scheduleApi.schedule.controller.validation.group.update.UpdateGroupSequence;
import scheduleApi.schedule.domain.Schedule;
import scheduleApi.schedule.service.ScheduleService;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/schedules")
@RestController
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping
    Schedule save(@Validated(SaveGroupSequence.class) @RequestBody ScheduleForm scheduleSaveForm) {
        return scheduleService.save(scheduleSaveForm);
    }

    @GetMapping("/{date}")
    List<Schedule> findByDate(@PathVariable LocalDate date) {
        return scheduleService.findByDate(date);
    }

    @PutMapping
    void update(@Validated(UpdateGroupSequence.class) @RequestBody ScheduleForm scheduleUpdateForm) {
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
