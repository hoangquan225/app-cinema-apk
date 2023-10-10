package com.example.appcinema.page.home;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.appcinema.R;
import com.example.appcinema.api.ConfigApi;
import com.example.appcinema.models.Film;
import com.example.appcinema.page.auth.LoginActivity;
import com.example.appcinema.page.auth.RegisterActivity;
import com.example.appcinema.responsive.FilmResponse;
import com.example.appcinema.utils.ProgressDialogUtils;
import com.example.appcinema.utils.SharedPreferencesUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    ConfigApi configApi = new ConfigApi();
    private RecyclerView recyclerView;
    private CustomAdapter adapter;
    private List<Film> filmList;
    private Context activity;
    Button btn_Login;
    Button btn_register;
    Button btn_Logout;

    @Override
    protected void onCreate(Bundle savedInstantState) {
        super.onCreate(savedInstantState);
        setContentView(R.layout.home_page);
        filmList = new ArrayList<>();
        this.activity = this;

        btn_Login = findViewById(R.id.btnLogin);
        btn_register = findViewById(R.id.btnRegister);
        btn_Logout = findViewById(R.id.btnLogout);

        String token = SharedPreferencesUtils.getStringToSharedPreferences(this, "token");

        ProgressDialog progressDialogLoadSession = new ProgressDialogUtils().createProgressDialog(activity);
        progressDialogLoadSession.show();
        configApi.getApiService().session(token).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d("TAG", "onResponse");
                int statusCode = response.code();
                progressDialogLoadSession.dismiss();
                if(statusCode == 200) {
                    Log.d("TAG", "Con phien dang nhap");
                    // oke
                    btn_Login.setVisibility(View.GONE);
                    btn_register.setVisibility(View.GONE);
                    btn_Logout.setVisibility(View.VISIBLE);

                    // api get category
                    ProgressDialog progressDialog = new ProgressDialogUtils().createProgressDialog(activity);
                    progressDialog.show();

                    configApi.getApiService().getFilms(null).enqueue(new Callback<FilmResponse>() {
                        @Override
                        public void onResponse(Call<FilmResponse> call, Response<FilmResponse> response) {
                            progressDialog.dismiss();
                            if (response.isSuccessful()) {
                                FilmResponse data = response.body();
                              Log.d("TAG", "Dữ liệu của bạn: " + data.getData().get(0).getName());
                                // Cập nhật dữ liệu vào Adapter và cập nhật ListView
                                for (int i = 0; i < data.getData().size(); i++) {
                                    filmList.add(data.getData().get(i));
                                }
                                adapter.notifyDataSetChanged();
                            } else {
                                progressDialog.dismiss();
                                Log.d("TAG", "error");
                                // Xử lý lỗi khi response không thành
                            }
                        }

                        @Override
                        public void onFailure(Call<FilmResponse> call, Throwable t) {
                            // Xử lý lỗi khi request thất bại
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "server bị lỗi", Toast.LENGTH_SHORT).show();
                            Log.d("TAG", "error api:  " + t);
                        }
                    });
                } else {
                    // het phien dang nhap
                    Log.d("TAG", "het phien dang nhap  ");

                    SharedPreferencesUtils.removeStringToSharedPreferences(activity, "token");
                    Intent intent = new Intent(activity, LoginActivity.class);
                    activity.startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                progressDialogLoadSession.dismiss();
                Toast.makeText(getApplicationContext(), "server bị lỗi", Toast.LENGTH_SHORT).show();
                Log.d("TAG", "error api:  " + t);
            }
        });
//        MutableLiveData<Boolean> isLoginLiveData = AuthManager.getInstance().getIsLoginLiveData();
//        isLoginLiveData.observe(this, new Observer<Boolean>() {
//            @Override
//            public void onChanged(Boolean isLogin) {
//                // Đây là nơi bạn xử lý sự thay đổi của trạng thái đăng nhập
//                if (isLogin) {
//                    // Người dùng đã đăng nhập
//
//                } else {
//                    // chua login
//
//                }
//            }
//        });

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        adapter = new CustomAdapter(filmList, this);
        recyclerView.setAdapter(adapter);

        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        btn_Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferencesUtils.removeStringToSharedPreferences(activity, "token");
                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }
}
