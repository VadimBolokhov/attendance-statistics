package ru.vbolokhov.attendancestatistics.dao;

import ru.vbolokhov.attendancestatistics.domain.CustomStats;
import ru.vbolokhov.attendancestatistics.domain.DailyStats;

import java.time.LocalDateTime;

/**
 * Stats DAO interface.
 * @author Vadim Bolokhov
 */
public interface StatsDao {

    DailyStats getDailyStats();

    CustomStats getStatsForTimeFrame(LocalDateTime from, LocalDateTime till);
}
