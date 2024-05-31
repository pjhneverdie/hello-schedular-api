package scheduleApi.schedule.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import scheduleApi.schedule.domain.ScheduleCreateRequestDto;
import scheduleApi.schedule.domain.ScheduleDto;
import scheduleApi.schedule.service.ScheduleService;

@RequiredArgsConstructor
@RequestMapping("/schedule")
@RestController
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping("/save")
    ScheduleDto save(@Valid @RequestBody ScheduleCreateRequestDto scheduleCreateRequestDto) {
        return scheduleService.save(scheduleCreateRequestDto);
    }
}
