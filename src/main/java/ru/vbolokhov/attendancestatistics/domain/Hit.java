package ru.vbolokhov.attendancestatistics.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Hit entity.
 * @author Vadim Bolokhov
 */
@Entity
@Table(name="hits")
public class Hit {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "user_id")
    private int userId;
    @Column(name = "page")
    private String page;
    @Column(name = "hit_date")
    private LocalDateTime hitDate;

    public Hit() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public LocalDateTime getHitDate() {
        return hitDate;
    }

    public void setHitDate(LocalDateTime hitDate) {
        this.hitDate = hitDate;
    }
}
