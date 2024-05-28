package scheduleApi.schedule.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import scheduleApi.schedule.domain.Schedule;
import scheduleApi.schedule.service.ScheduleService;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/schedule")
@RestController
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping("/save")
    Schedule save(@Valid @RequestBody Schedule schedule) {
        return scheduleService.save(schedule);
    }
}
