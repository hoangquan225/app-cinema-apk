package com.example.appcinema.responsive;
import com.example.appcinema.models.Film;
import com.example.appcinema.models.Ticket;

public class TicketResponse {
    private Ticket data;
    private Number status;

    public TicketResponse(Ticket data, Number status) {
        this.data = data;
        this.status = status;
    }

    // Getter cho data
    public Ticket getData() {
        return data;
    }

    // Setter cho data
    public void setData(Ticket data) {
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
