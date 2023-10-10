package com.example.appcinema.api;

import com.example.appcinema.services.ApiService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConfigApi {
    private ApiService apiService;

    public ConfigApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://cinema-server-wcbv.onrender.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.apiService = retrofit.create(ApiService.class);
    }

    public ApiService getApiService() {
        return apiService;
    }
}
