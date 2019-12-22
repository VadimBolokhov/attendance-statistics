package ru.vbolokhov.attendancestatistics.domain;

/**
 * Daily stats class.
 * @author Vadim Bolokhov
 */
public class DailyStats {
    /** Hits for the current day */
    private int dailyHits;
    /** Unique users for the current day */
    private int uniqueUsers;

    public DailyStats() {
    }

    public int getDailyHits() {
        return dailyHits;
    }

    public void setDailyHits(int dailyHits) {
        this.dailyHits = dailyHits;
    }

    public int getUniqueUsers() {
        return uniqueUsers;
    }

    public void setUniqueUsers(int uniqueUsers) {
        this.uniqueUsers = uniqueUsers;
    }
}
