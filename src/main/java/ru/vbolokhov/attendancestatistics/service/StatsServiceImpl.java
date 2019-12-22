package ru.vbolokhov.attendancestatistics.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vbolokhov.attendancestatistics.dao.StatsDao;
import ru.vbolokhov.attendancestatistics.domain.CustomStats;
import ru.vbolokhov.attendancestatistics.domain.DailyStats;

import java.time.LocalDateTime;

/**
 * An implementation of StatsService interface.
 * @author Vadim Bolokhov
 */
@Service
public class StatsServiceImpl implements StatsService {

    private final StatsDao dao;

    @Autowired
    public StatsServiceImpl(StatsDao dao) {
        this.dao = dao;
    }

    @Override
    public DailyStats getDailyStats() {
        return this.dao.getDailyStats();
    }

    @Override
    public CustomStats getCustomStats(LocalDateTime from, LocalDateTime till) {
        return this.dao.getStatsForTimeFrame(from, till);
    }
}
