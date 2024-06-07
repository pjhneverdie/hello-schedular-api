package scheduleApi.schedule.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import scheduleApi.schedule.controller.validation.ValidationOrder;
import scheduleApi.schedule.domain.Schedule;
import scheduleApi.schedule.controller.form.ScheduleSaveForm;
import scheduleApi.schedule.controller.form.ScheduleUpdateForm;
import scheduleApi.schedule.service.ScheduleService;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/schedules")
@RestController
public class ScheduleController {
    private final ScheduleService scheduleService;


    @PostMapping
    Schedule save(@Validated(ValidationOrder.class) @RequestBody ScheduleSaveForm scheduleSaveForm, BindingResult bindingResult) {
        return scheduleService.save(scheduleSaveForm);
    }

    @GetMapping("/{date}")
    List<Schedule> findByDate(@PathVariable LocalDate date) {
        return scheduleService.findByDate(date);
    }

    @PutMapping
    void update(@Validated(ValidationOrder.class) @RequestBody ScheduleUpdateForm scheduleUpdateForm) {
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
