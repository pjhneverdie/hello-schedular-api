package scheduleApi.schedule.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import scheduleApi.schedule.domain.Schedule;
import scheduleApi.schedule.service.ScheduleService;

@RequiredArgsConstructor
@RequestMapping("/schedule")
@RestController
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping("/save")
    Schedule save(@Validated @RequestBody Schedule schedule) {
        return scheduleService.save(schedule);
    }
}
