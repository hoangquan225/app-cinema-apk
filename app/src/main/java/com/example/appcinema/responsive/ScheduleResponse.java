package com.example.appcinema.responsive;
import com.example.appcinema.models.Schedule;

import java.util.List;

public class ScheduleResponse {
    private List<Schedule> data;
    private Number status;
    private Number count;

    public ScheduleResponse(List<Schedule> data, Number status, Number count) {
        this.data = data;
        this.status = status;
        this.count = count;
    }

    public List<Schedule> getData() {
        return data;
    }

    public void setData(List<Schedule> data) {
        this.data = data;
    }

    public Number getStatus() {
        return status;
    }

    public void setStatus(Number status) {
        this.status = status;
    }

    public Number getCount() {
        return count;
    }

    public void setCount(Number count) {
        this.count = count;
    }
}
