package com.example.appcinema.models;

public class Schedule {
    private String id;
    private Long showDate;

    private Integer nSeat;

    public Schedule() {
    }

    public Schedule(String id, Long showDate, Integer nSeat) {
        this.id = id;
        this.showDate = showDate;
        this.nSeat = nSeat;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getShowDate() {
        return showDate;
    }

    public void setShowDate(Long showDate) {
        this.showDate = showDate;
    }

    public Integer getnSeat() {
        return nSeat;
    }

    public void setnSeat(Integer nSeat) {
        this.nSeat = nSeat;
    }
}
