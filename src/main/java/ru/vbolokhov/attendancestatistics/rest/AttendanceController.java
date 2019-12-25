package ru.vbolokhov.attendancestatistics.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import ru.vbolokhov.attendancestatistics.domain.Hit;
import ru.vbolokhov.attendancestatistics.domain.CustomStats;
import ru.vbolokhov.attendancestatistics.domain.DailyStats;
import ru.vbolokhov.attendancestatistics.repository.HitRepository;
import ru.vbolokhov.attendancestatistics.service.StatsService;

import java.time.LocalDateTime;

@RestController
public class AttendanceController {

    private final StatsService statsService;

    private final HitRepository repository;

    @Autowired
    public AttendanceController(StatsService service, HitRepository rep) {
        this.statsService = service;
        this.repository = rep;
    }

    @PostMapping("/counter")
    public DailyStats createEntry(@RequestBody Hit hit) {
        hit.setHitDate(LocalDateTime.now());
        this.repository.save(hit);
        return this.statsService.getDailyStats();
    }

    @GetMapping("/stats")
    public CustomStats getStatistics(
            @RequestParam("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
            @RequestParam("till") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime till
            ) {
        return this.statsService.getCustomStats(from, till);
    }
}
