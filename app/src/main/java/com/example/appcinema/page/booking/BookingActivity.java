package com.example.appcinema.page.booking;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.example.appcinema.R;
import com.example.appcinema.api.ConfigApi;
import com.example.appcinema.models.User;
import com.example.appcinema.responsive.*;
import com.example.appcinema.utils.ProgressDialogUtils;
import com.example.appcinema.utils.SharedPreferencesUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

public class BookingActivity extends AppCompatActivity {
    ConfigApi configApi = new ConfigApi();

    private User user;
    private GridLayout gridLayout;
    private List<Button> selectedSeats = new ArrayList<>();
    private int totalPrice = 0; // Biến để lưu trữ tổng giá tiền
    List<Integer> seatNumbers =  new ArrayList<>();
    TextView totalTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        TextView textView = findViewById(R.id.textHeader);
        Button btnBookTicket = findViewById(R.id.btnBookTicket);
        totalTextView = findViewById(R.id.totalTextView);
        totalTextView.setText("Tổng giá tiền: " + totalPrice + " VND");

        gridLayout = findViewById(R.id.gridLayout);

        Intent intent = getIntent();
        String name = intent.getStringExtra("filmName");
        String scheduleId = intent.getStringExtra("scheduleId");
        String showTime = intent.getStringExtra("showTime");
        String filmId = intent.getStringExtra("filmId");
        String nSeat = intent.getStringExtra("nSeat");
        textView.setText(name);

        Log.d("TAG","scheduleId| " +scheduleId+"|showTime: "+showTime+" |filmId:"+filmId+" |nSeat: "+nSeat);

        ProgressDialog progressDialog = new ProgressDialogUtils().createProgressDialog(this);
        progressDialog.show();
        configApi.getApiService().getSeatOfSchedule(scheduleId, showTime).enqueue(new Callback<SeatResponse>() {
            @Override
            public void onResponse(Call<SeatResponse> call, Response<SeatResponse> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {
                    SeatResponse data = response.body();
                    for (int i = 0; i < data.getData().size(); i++) {
                        seatNumbers.add(data.getData().get(i));
                        Log.d("TAG", seatNumbers.get(i).toString());
                    }
                    initSeat();
                } else {
                    Log.d("TAG", "error");
                }
            }
            @Override
            public void onFailure(Call<SeatResponse> call, Throwable t) {
                Log.d("TAG", "error api:  " + t);
                progressDialog.dismiss();
            }
        });

//        int seatCount = 16;
//        for (int i = 1; i <= seatCount; i++) {
//            Button seatButton = new Button(this);
//            seatButton.setText(""+i);
//            seatButton.setTag(String.valueOf(i)); // Lưu index của ghế vào tag
//            seatButton.setBackgroundResource(R.drawable.gray);
//
//            if (seatNumbers.contains(i)) {
//                seatButton.setBackgroundResource(R.drawable.orange); // Màu mặc định là yellow
//            } else {
//                seatButton.setBackgroundResource(R.drawable.gray); // Màu mặc định là green
//            }
//            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
////            params.width = GridLayout.LayoutParams.WRAP_CONTENT;
////            params.height = GridLayout.LayoutParams.WRAP_CONTENT;
//            params.width = 200;
//            params.height = 200;
//            params.setMargins(8, 8, 8, 8); // Điều chỉnh khoảng cách giữa các ô ghế
//            seatButton.setLayoutParams(params);
//
//            // Lắng nghe sự kiện khi người dùng nhấn vào nút ghế
//            seatButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    String seatIndex = v.getTag().toString();
//                    if (seatNumbers.contains(seatButton)) {
//                        Toast.makeText(BookingActivity.this, "Ghế này không khả dụng", Toast.LENGTH_SHORT).show();
//                    } else if (selectedSeats.contains(seatButton)) {
//                        selectedSeats.remove(seatButton);
//                        seatButton.setBackgroundResource(R.drawable.gray) ;
//                        totalPrice -= 99000;
//                    } else {
//                        selectedSeats.add(seatButton);
//                        seatButton.setBackgroundResource(R.drawable.red);
//                        totalPrice += 99000;
//                    }
//                    totalTextView.setText("Tổng giá tiền: " + totalPrice + " VND");
//                }
//
//            });
//            // Thêm Button vào GridLayout
//            gridLayout.addView(seatButton);
//        }

        btnBookTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Thực hiện xử lý đặt vé ở đây (gọi API hoặc thực hiện tác vụ khác)
                createTicket();
            }
        });

    }

    private void initSeat() {
        Intent intent = getIntent();
        String nSeat = intent.getStringExtra("nSeat");
        Integer nSeatInt = Integer.parseInt(nSeat);

        for (int i = 1; i <= nSeatInt; i++) {
            Button seatButton = new Button(this);
            seatButton.setText(""+i);
            seatButton.setTag(String.valueOf(i)); // Lưu index của ghế vào tag
            seatButton.setBackgroundResource(R.drawable.gray);

            if (seatNumbers.contains(i)) {
                seatButton.setBackgroundResource(R.drawable.orange); // Màu mặc định là yellow
            } else {
                seatButton.setBackgroundResource(R.drawable.gray); // Màu mặc định là green
            }
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
//            params.width = GridLayout.LayoutParams.WRAP_CONTENT;
//            params.height = GridLayout.LayoutParams.WRAP_CONTENT;
            params.width = 200;
            params.height = 200;
            params.setMargins(8, 8, 8, 8); // Điều chỉnh khoảng cách giữa các ô ghế
            seatButton.setLayoutParams(params);

            // Lắng nghe sự kiện khi người dùng nhấn vào nút ghế
            seatButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String seatIndex =  seatButton.getTag().toString();
                    Integer seatInt = Integer.parseInt(seatIndex);

                    if (seatNumbers.contains(seatInt)) {
                        Toast.makeText(BookingActivity.this, "Ghế này không khả dụng", Toast.LENGTH_SHORT).show();
                    } else if (selectedSeats.contains(seatButton)) {
                        selectedSeats.remove(seatButton);
                        seatButton.setBackgroundResource(R.drawable.gray) ;
                        totalPrice -= 99000;
                    } else {
                        selectedSeats.add(seatButton);
                        seatButton.setBackgroundResource(R.drawable.red);
                        totalPrice += 99000;
                    }
                    totalTextView.setText("Tổng giá tiền: " + totalPrice + " VND");
                }

            });
            // Thêm Button vào GridLayout
            gridLayout.addView(seatButton);
        }
    }

    private void createTicket() {
        // Tạo một danh sách các số ghế đã chọn
        List<Integer> seat = new ArrayList<>();
        for (Button seatButton : selectedSeats) {
            String seatIndex = seatButton.getTag().toString();
            seat.add(Integer.parseInt(seatIndex));
        }

        Intent intent = getIntent();
        String name = intent.getStringExtra("filmName");
        String scheduleId = intent.getStringExtra("scheduleId");
        String showTime = intent.getStringExtra("showTime");
        String showDate = intent.getStringExtra("showDate");
        String filmId = intent.getStringExtra("filmId");

        ProgressDialog progressDialog = new ProgressDialogUtils().createProgressDialog(this);
        progressDialog.show();

        String userId = SharedPreferencesUtils.getStringToSharedPreferences(this, "userId");
        Log.d("TAG", "userId |  " + userId);

        configApi.getApiService().createTicket(filmId, scheduleId, seat, totalPrice, userId, showTime, Long.parseLong(showDate)).enqueue(new Callback<TicketResponse>() {
            @Override
            public void onResponse(Call<TicketResponse> call, Response<TicketResponse> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {
                    Toast.makeText(BookingActivity.this, "Đặt vé thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Log.d("TAG", "error");
                }
            }

            @Override
            public void onFailure(Call<TicketResponse> call, Throwable t) {
                Log.d("TAG", "error api:  " + t);
                progressDialog.dismiss();
            }
        });
    }
}
