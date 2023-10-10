package com.example.appcinema.models;

import java.util.List;

public class Ticket {
    private String id;
    private String userId;
    private int status;
    private String filmId;
    private String scheduleId;
    private List<Integer> seat;
    private String showTime;
    private Integer price;
    private Long createDate;
    private Long updateDate;

    public Ticket() {
    }

    public Ticket(String id, String userId, int status, String filmId, String scheduleId, List<Integer> seat, String showTime, Integer price, Long createDate, Long updateDate) {
        this.id = id;
        this.userId = userId;
        this.status = status;
        this.filmId = filmId;
        this.scheduleId = scheduleId;
        this.seat = seat;
        this.showTime = showTime;
        this.price = price;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getFilmId() {
        return filmId;
    }

    public void setFilmId(String filmId) {
        this.filmId = filmId;
    }

    public String getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }

    public List<Integer> getSeat() {
        return seat;
    }

    public void setSeat(List<Integer> seat) {
        this.seat = seat;
    }

    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }

    public Long getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Long updateDate) {
        this.updateDate = updateDate;
    }
}
