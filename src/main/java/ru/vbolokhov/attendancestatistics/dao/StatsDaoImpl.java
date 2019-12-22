package ru.vbolokhov.attendancestatistics.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import ru.vbolokhov.attendancestatistics.domain.CustomStats;
import ru.vbolokhov.attendancestatistics.domain.DailyStats;

import java.time.LocalDateTime;
import java.util.HashMap;

/**
 * PostgreSQL implementation of StatsDao interface.
 * @author Vadim Bolokhov
 */
@Repository
@ConditionalOnProperty(name = "spring.datasource.driver-class-name", havingValue = "org.postgresql.Driver")
public class StatsDaoImpl implements StatsDao {

    private static final String DAILY_STATS_QUERY =
            "SELECT COUNT(*) AS daily_hits, COUNT(DISTINCT user_id) AS users FROM hits" +
                    " WHERE hit_date >= current_date;";

    private static final String CUSTOM_STATS_QUERY =
            "SELECT v.hits_during_period, v.unique_users, r.regular_users FROM" +
                    " (" +
                    "   SELECT COUNT(DISTINCT user_id) AS unique_users, COUNT(*) AS hits_during_period FROM hits" +
                    "   WHERE hit_date >= :start AND hit_date <= :end" +
                    " ) AS v," +
                    " ( " +
                    "   SELECT count(*) AS regular_users FROM" +
                    "   (" +
                    "     SELECT user_id, COUNT(DISTINCT page) FROM hits" +
                    "     WHERE hit_date >= :start AND hit_date < :end" +
                    "     GROUP BY user_id HAVING COUNT(DISTINCT page) >= 10" +
                    "   ) AS c" +
                    " ) AS r;";

    private final NamedParameterJdbcOperations jdbc;

    private RowMapper<DailyStats> dailyStatsMapper = (rs, i) -> {
        DailyStats dStats = new DailyStats();
        dStats.setDailyHits(rs.getInt("daily_hits"));
        dStats.setUniqueUsers(rs.getInt("users"));
        return dStats;
    };

    private RowMapper<CustomStats> customStatsMapper = (rs, i) -> {
        CustomStats cStats = new CustomStats();
        cStats.setOverallHits(rs.getInt("hits_during_period"));
        cStats.setUniqueUsers(rs.getInt("unique_users"));
        cStats.setRegularUsers(rs.getInt("regular_users"));
        return cStats;
    };

    @Autowired
    public StatsDaoImpl(NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public DailyStats getDailyStats() {
        return this.jdbc.queryForObject(DAILY_STATS_QUERY, new HashMap<>(0), this.dailyStatsMapper);
    }

    @Override
    public CustomStats getStatsForTimeFrame(LocalDateTime from, LocalDateTime till) {
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("start", from)
                .addValue("end", till);
        return this.jdbc.queryForObject(CUSTOM_STATS_QUERY, params, this.customStatsMapper);
    }
}
