package com.example.appcinema.responsive;
import com.example.appcinema.models.Film;

import java.util.List;

public class SeatResponse {
    private List<Integer> data;
    private Number status;

    public SeatResponse(List<Integer> data, Number status) {
        this.data = data;
        this.status = status;
    }

    public List<Integer> getData() {
        return data;
    }

    public void setData(List<Integer> data) {
        this.data = data;
    }

    public Number getStatus() {
        return status;
    }

    public void setStatus(Number status) {
        this.status = status;
    }

}
