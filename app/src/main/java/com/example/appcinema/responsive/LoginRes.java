package com.example.appcinema.responsive;

import com.google.gson.annotations.SerializedName;

public class LoginRes {
    @SerializedName("token")
    private String token;

    @SerializedName("status")
    private int status;

    @SerializedName("userId")
    private String userId;

    public int getStatus() {
        return status;
    }

    public String getToken() {
        return token;
    }

    public String getUserId() {
        return userId;
    }
}
