package ru.vbolokhov.attendancestatistics.service;

import ru.vbolokhov.attendancestatistics.domain.CustomStats;
import ru.vbolokhov.attendancestatistics.domain.DailyStats;

import java.time.LocalDateTime;

/**
 * Stats service interface.
 * @author Vadim Bolokhov
 */
public interface StatsService {

    DailyStats getDailyStats();

    CustomStats getCustomStats(LocalDateTime from, LocalDateTime till);
}
