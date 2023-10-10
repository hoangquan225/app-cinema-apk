package com.example.appcinema.responsive;
import com.example.appcinema.models.Film;

public class FilmInfoResponse {
    private Film data;
    private Number status;

    public FilmInfoResponse(Film data, Number status) {
        this.data = data;
        this.status = status;
    }

    // Getter cho data
    public Film getData() {
        return data;
    }

    // Setter cho data
    public void setData(Film data) {
        this.data = data;
    }

    // Getter cho status
    public Number getStatus() {
        return status;
    }

    // Setter cho status
    public void setStatus(Number status) {
        this.status = status;
    }

}
