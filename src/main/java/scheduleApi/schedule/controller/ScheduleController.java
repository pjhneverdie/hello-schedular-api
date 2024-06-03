package scheduleApi.schedule.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import scheduleApi.schedule.repository.dto.ScheduleSaveDto;
import scheduleApi.schedule.repository.dto.ScheduleUpdateDto;
import scheduleApi.schedule.service.ScheduleService;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/schedule")
@RestController
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping("/save")
    ScheduleUpdateDto save(@Valid @RequestBody ScheduleSaveDto scheduleSaveDto) {
        return scheduleService.save(scheduleSaveDto);
    }

    @GetMapping("/find-by-date/{date}")
    List<ScheduleUpdateDto> findByDate(@PathVariable  LocalDate date) {
        return scheduleService.findByDate(date);
    }

    @PostMapping("/update")
    ScheduleUpdateDto update(@Valid @RequestBody ScheduleUpdateDto scheduleSaveDto) {
        return scheduleService.update(scheduleSaveDto);
    }

    @PostMapping("/delete/{id}")
    void delete(@PathVariable Integer id) {
        scheduleService.delete(id);
    }

    @PostMapping("/delete-by-date/{date}")
    void deleteByDate(@PathVariable LocalDate date) {
        scheduleService.deleteByDate(date);
    }
}
