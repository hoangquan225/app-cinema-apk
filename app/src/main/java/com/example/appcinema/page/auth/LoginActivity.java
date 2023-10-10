package com.example.appcinema.page.auth;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.appcinema.R;
import com.example.appcinema.api.ConfigApi;
import com.example.appcinema.page.home.HomeActivity;
import com.example.appcinema.responsive.LoginRes;
import com.example.appcinema.singleton.AuthManager;
import com.example.appcinema.utils.ProgressDialogUtils;
import com.example.appcinema.utils.SharedPreferencesUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;
    private Button btn_register;
    ConfigApi configApi = new ConfigApi();
    private Context loginContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        this.loginContext = this;

        // Ánh xạ các thành phần trong giao diện
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btn_register = findViewById(R.id.btnRegister);

        // Đăng ký bắt sự kiện khi nút đăng nhập được nhấn
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy tên đăng nhập và mật khẩu từ EditText tương ứng
                String email = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                Log.d("TAG", "email|password: " + email + "|" + password);

                ProgressDialog progressDialog = ProgressDialogUtils.createProgressDialog(loginContext);
                progressDialog.show();
                configApi.getApiService().login(email, password).enqueue(new Callback<LoginRes>() {
                    @Override
                    public void onResponse(Call<LoginRes> call, Response<LoginRes> response) {
                        if (response.isSuccessful()) {
                            String token = response.body().getToken();
                            String userId = response.body().getUserId();
                            if(token.equals("1")) {
                                Toast.makeText(getApplicationContext(), "Tài khoản hoặc mật khẩu sai", Toast.LENGTH_SHORT).show();
                            } else if(token.equals("-1")) {
                                Toast.makeText(getApplicationContext(), "Không tồn tại tài khoản", Toast.LENGTH_SHORT).show();
                            } else if(!token.equals("")) {
                                // Lưu giá trị token vào SharedPreferences
                                SharedPreferencesUtils.saveStringToSharedPreferences(loginContext,"token", token);
                                SharedPreferencesUtils.saveStringToSharedPreferences(loginContext,"userId", userId);
                                Log.d("TAG", "userId |  " + userId);

                                // change value isLogin
                                AuthManager.getInstance().setLoginStatus(true);

                                Toast.makeText(getApplicationContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(loginContext, HomeActivity.class);
                                loginContext.startActivity(intent);
                            }
                            progressDialog.dismiss();
                        } else {
                            progressDialog.dismiss();
                            Log.d("TAG", "error");
                            Toast.makeText(getApplicationContext(), "server bị lỗi", Toast.LENGTH_SHORT).show();
                            // Xử lý lỗi khi response không thành
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginRes> call, Throwable t) {
                        // Xử lý lỗi khi request thất bại
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), "server bị lỗi", Toast.LENGTH_SHORT).show();
                        Log.d("TAG", "error api:  " + t);
                    }
                });
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}
