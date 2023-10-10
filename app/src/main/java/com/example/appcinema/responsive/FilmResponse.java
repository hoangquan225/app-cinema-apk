package com.example.appcinema.responsive;
import com.example.appcinema.models.Film;

import java.util.List;

public class FilmResponse {
    private List<Film> data;
    private Number status;
    private Number count;

    public FilmResponse(List<Film> data, Number status, Number count) {
        this.data = data;
        this.status = status;
        this.count = count;
    }

    public List<Film> getData() {
        return data;
    }

    public void setData(List<Film> data) {
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

    public void setSCount(Number status) {
        this.count = count;
    }

}
