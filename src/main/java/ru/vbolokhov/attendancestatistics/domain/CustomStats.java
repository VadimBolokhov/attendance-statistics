package ru.vbolokhov.attendancestatistics.domain;

/**
 * Custom stats class.
 * @author Vadim Bolokhov
 */
public class CustomStats {
    /** Total number of hits */
    private int overallHits;
    /** Number of unique users */
    private int uniqueUsers;
    /** Number of regular users */
    private int regularUsers;

    public CustomStats() {
    }

    public int getOverallHits() {
        return overallHits;
    }

    public void setOverallHits(int overallHits) {
        this.overallHits = overallHits;
    }

    public int getUniqueUsers() {
        return uniqueUsers;
    }

    public void setUniqueUsers(int uniqueUsers) {
        this.uniqueUsers = uniqueUsers;
    }

    public int getRegularUsers() {
        return regularUsers;
    }

    public void setRegularUsers(int regularUsers) {
        this.regularUsers = regularUsers;
    }
}
