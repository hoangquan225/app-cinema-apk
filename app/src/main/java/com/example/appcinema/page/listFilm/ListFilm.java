package com.example.appcinema.page.listFilm;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.appcinema.R;
import com.example.appcinema.api.ConfigApi;
import com.example.appcinema.models.Film;
import com.example.appcinema.page.booking.BookingActivity;
import com.example.appcinema.page.booking.ScheduleActivity;
import com.example.appcinema.responsive.FilmInfoResponse;
import com.example.appcinema.utils.ProgressDialogUtils;
import com.squareup.picasso.Picasso;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


import java.util.ArrayList;
import java.util.List;

public class ListFilm extends AppCompatActivity {
    private ConfigApi configApi = new ConfigApi();
    private Film film;

    private List<Film> filmList;

    private FilmInfoAdapter filmInfoAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_info);

        TextView textName = findViewById(R.id.textName);
        ImageView imageView = findViewById(R.id.imageView);
        TextView textDescription = findViewById(R.id.textDescription);
        Button btnBook = findViewById(R.id.btnBook);

//        film = new Film();
//        recyclerView = findViewById(R.id.recyclerView1);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        filmInfoAdapter = new FilmInfoAdapter(film, this);
//        recyclerView.setAdapter(filmInfoAdapter);


        Intent intent = getIntent();
        String filmId = intent.getStringExtra("filmId");
        Log.d("TAG", "filmId|" + filmId);

//        String name = intent.getStringExtra("name");
//        textView.setText(textView.getText().toString() + " " + name);

        ProgressDialog progressDialog = new ProgressDialogUtils().createProgressDialog(this);
        progressDialog.show();
        configApi.getApiService().getFilmById1(filmId).enqueue(new Callback<FilmInfoResponse>() {
            @Override
            public void onResponse(Call<FilmInfoResponse> call, Response<FilmInfoResponse> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {
                    FilmInfoResponse data = response.body();
                    film = data.getData();
                    Log.d("TAG", film.getName());
                    if (film != null) {
                        textName.setText(film.getName());
                        textDescription.setText(film.getDescription());
                        Log.d("TAG", "image course: " + film.getThumbnail());
                        Picasso.get().load(film.getThumbnail()).into(imageView, new com.squareup.picasso.Callback() {
                            @Override
                            public void onSuccess() {
                                Log.d("TAG", "onSuccess: ");
                            }
                            @Override
                            public void onError(Exception e) {
                                Log.d("TAG", "onError: " + e);
                            }
                        });
                        btnBook.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // Xử lý sự kiện khi nút "Làm ngay" được nhấn
                                Log.d("TAG", "id film: " + film.get_id());
//                                Intent intent = new Intent(ListFilm.this, BookingActivity.class);
                                Intent intent = new Intent(ListFilm.this, ScheduleActivity.class);
                                intent.putExtra("filmName", film.getName());
                                intent.putExtra("filmId", film.get_id());
                                startActivity(intent);
                            }
                        });
                    }
//                    filmInfoAdapter.notifyDataSetChanged();
                } else {
                    Log.d("TAG", "error");
                }
            }

            @Override
            public void onFailure(Call<FilmInfoResponse> call, Throwable t) {
                Log.d("TAG", "error api:  " + t);
                progressDialog.dismiss();
            }
        });

//        configApi.getApiService().getFilmById1(filmId).enqueue(new Callback<FilmInfoResponse>() {
//            @Override
//            public void onResponse(Call<FilmInfoResponse> call, Response<FilmInfoResponse> response) {
//                progressDialog.dismiss();
//                if (response.isSuccessful()) {
//                    FilmInfoResponse data = response.body();
//                    film = data.getData();
//                    Log.d("TAG", film.getName());
//                    if (film != null) {
//                        textName.setText(film.getName());
//                        textDescription.setText(film.getDescription());
//                        Log.d("TAG", "image course: " + film.getThumbnail());
//                        Picasso.get().load(film.getThumbnail()).into(imageView, new com.squareup.picasso.Callback() {
//                            @Override
//                            public void onSuccess() {
//                                Log.d("TAG", "onSuccess: ");
//                            }
//                            @Override
//                            public void onError(Exception e) {
//                                Log.d("TAG", "onError: " + e);
//                            }
//                        });
//
//                    }
////                    filmInfoAdapter.notifyDataSetChanged();
//                } else {
//                    Log.d("TAG", "error");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<FilmInfoResponse> call, Throwable t) {
//                Log.d("TAG", "error api:  " + t);
//                progressDialog.dismiss();
//            }
//        });
    }
}
