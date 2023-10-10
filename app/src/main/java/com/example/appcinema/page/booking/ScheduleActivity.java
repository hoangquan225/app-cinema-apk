package com.example.appcinema.page.booking;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.example.appcinema.R;
import com.example.appcinema.api.ConfigApi;
import com.example.appcinema.models.Schedule;
import com.example.appcinema.models.User;
import com.example.appcinema.responsive.ScheduleResponse;
import com.example.appcinema.responsive.TicketResponse;
import com.example.appcinema.utils.DateUtils;
import com.example.appcinema.utils.ProgressDialogUtils;
import com.example.appcinema.utils.SharedPreferencesUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

public class ScheduleActivity extends AppCompatActivity {
    ConfigApi configApi = new ConfigApi();
    private Context scheduleContext;
    Schedule scheduleInfo;
    String nSeat;
    String filmName;
    String filmId;
    private List<Schedule> scheduleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        scheduleList = new ArrayList<>();
        this.scheduleContext = this;
        TextView textView = findViewById(R.id.textHeader);

        GridLayout gridLayoutDate = findViewById(R.id.gridLayoutDate); // GridLayout trong giao diện
        RadioGroup radioTime = findViewById(R.id.radioTime);


        Intent intent = getIntent();
        filmId = intent.getStringExtra("filmId");
        filmName = intent.getStringExtra("filmName");
        textView.setText(filmName);

        ProgressDialog progressDialog = ProgressDialogUtils.createProgressDialog(scheduleContext);
        progressDialog.show();

        configApi.getApiService().getSchedule(filmId).enqueue(new Callback<ScheduleResponse>() {
            @Override
            public void onResponse(Call<ScheduleResponse> call, Response<ScheduleResponse> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {
                    ScheduleResponse data = response.body();

                    Log.d("TAG", "Dữ liệu của bạn: " + data.getData().get(1).getShowDate());
                    for (int i = 0; i < data.getData().size(); i++) {
                        scheduleList.add(data.getData().get(i));
                        long showDate = data.getData().get(i).getShowDate();
                        int[] formattedDate = DateUtils.convertTimeMiliseconds(showDate);

                        Button timeButton = new Button(scheduleContext);
                        timeButton.setText(formattedDate[0]+"/"+ formattedDate[1]);
                        timeButton.setTag(new Schedule(data.getData().get(i).getId(), data.getData().get(i).getShowDate(), data.getData().get(i).getnSeat())); // Lưu ID của thời gian vào tag của nút
                        timeButton.setBackgroundResource(R.drawable.radius);
                        timeButton.setGravity(Gravity.CENTER);

                        GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                        params.width = GridLayout.LayoutParams.WRAP_CONTENT;
                        params.height = GridLayout.LayoutParams.WRAP_CONTENT;
                        params.rightMargin = 16;
                        timeButton.setLayoutParams(params);

                        timeButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                for (int j = 0; j < gridLayoutDate.getChildCount(); j++) {
                                    View child = gridLayoutDate.getChildAt(j);
                                    if (child instanceof Button) {
                                        Button button = (Button) child;
                                        if (button.getTag().equals(v.getTag())) {
                                            button.setBackgroundResource(R.drawable.radius_6);
                                            scheduleInfo = (Schedule) v.getTag();
                                        } else {
                                            button.setBackgroundResource(R.drawable.radius);

                                        }
                                    }
                                }
                            }
                        });
                        gridLayoutDate.addView(timeButton);
                    }
                } else {
                    Log.d("TAG", "error");
                }
            }
            @Override
            public void onFailure(Call<ScheduleResponse> call, Throwable t) {
                Log.d("TAG", "error api:  " + t);
                progressDialog.dismiss();
            }
        });

        Button btnSchedule = findViewById(R.id.btnSchedule);
        btnSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedGenderId = radioTime.getCheckedRadioButtonId();
                RadioButton selectedRadioButton = findViewById(selectedGenderId);
                if (!scheduleInfo.getId().isEmpty() && selectedRadioButton != null) {
                    String time = selectedRadioButton.getTag().toString();
                    if(time.equals("9")) {
                        time = "09:00";
                    }else{
                        time = time + ":00";
                    }
                    Intent intent = new Intent(scheduleContext, BookingActivity.class);
                    intent.putExtra("scheduleId", scheduleInfo.getId());
                    intent.putExtra("nSeat", scheduleInfo.getnSeat().toString());
                    intent.putExtra("showDate", scheduleInfo.getShowDate().toString());
                    intent.putExtra("showTime", time);
                    intent.putExtra("filmName", filmName);
                    intent.putExtra("filmId", filmId);
                    startActivity(intent);
                }
            }
        });
    }
}
