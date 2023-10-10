package com.example.appcinema.responsive;
import com.example.appcinema.models.User;

public class UserResponse {
    private Object data;
    private Number status;

    public UserResponse(User data, Number status) {
        this.data = data;
        this.status = status;
    }

    // Getter cho data
    public Object getData() {
        return data;
    }

    // Setter cho data
    public void setData(User data) {
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
