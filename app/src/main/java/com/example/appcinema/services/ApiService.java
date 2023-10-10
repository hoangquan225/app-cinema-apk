package com.example.appcinema.services;

import com.example.appcinema.models.Film;
import com.example.appcinema.models.User;
import com.example.appcinema.responsive.*;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface ApiService {
//    @GET("api/endpoint")
//    Call<YourResponseModel> yourGetMethod(@Query("param1") String param1, @Query("param2") int param2);

    @GET("/api/film/get-all-film")
    Call<FilmResponse> getFilms(@Query("status") Integer status);

    @FormUrlEncoded
    @GET("/api-mobile/get-film-by-id")
    Call<FilmInfoResponse> getFilmById(@Query("filmId") String filmId);

    @FormUrlEncoded
    @POST("/api-mobile/get-film-by-id")
    Call<FilmInfoResponse> getFilmById1(@Field("filmId") String filmId);

    @FormUrlEncoded
    @POST("/api-mobile/get-schedule")
    Call<ScheduleResponse> getSchedule(@Field("filmId") String filmId);

    @FormUrlEncoded
    @POST("/api-mobile/get-seat-of-schedule")
    Call<SeatResponse> getSeatOfSchedule(
            @Field("scheduleId") String scheduleId,
            @Field("showTime") String showTime
    );

    @FormUrlEncoded
    @POST("/api-mobile/login")
    Call<LoginRes> login(@Field("email") String email, @Field("password") String password);

    @FormUrlEncoded
    @POST("/api-mobile/register")
    Call<LoginRes> register(
            @Field("email") String email,
            @Field("password") String password,
            @Field("name") String name,
            @Field("phoneNumber") String phoneNumber,
            @Field("gender") int gender
    );

    @FormUrlEncoded
    @POST("/api/ticket/create-ticket")
    Call<TicketResponse> createTicket(
            @Field("filmId") String filmId,
            @Field("scheduleId") String scheduleId,
            @Field("seat") List<Integer> seat,
            @Field("price") int price,
            @Field("userId") String userId,
            @Field("showTime") String showTime,
            @Field("showDate") Long showDate
    );

    @FormUrlEncoded
    @POST("/api-mobile/user")
    Call<UserResponse> user(@Field("token") String token);

    @FormUrlEncoded
    @POST("/api-mobile/session")
    Call<Void> session(@Field("token") String token);

    // Các phương thức API khác...
}
