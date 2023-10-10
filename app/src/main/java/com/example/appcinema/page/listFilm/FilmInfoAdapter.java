package com.example.appcinema.page.listFilm;

import android.content.Context;
import android.os.Build;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.example.appcinema.R;
import com.example.appcinema.models.Film;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;


public class FilmInfoAdapter extends RecyclerView.Adapter<CustomViewHolder> {

    private Film film;
    private Context activity;

    public FilmInfoAdapter(Film film, FragmentActivity activity) {
        this.film = film;
        this.activity = activity;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_film_info, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder  holder, int position) {
        Film item = film;
//        Film item = filmList.get(position);

        Log.d("TAG", "image course: " + item.getThumbnail());
        Picasso.get().load(item.getThumbnail()).into(holder.imageView, new Callback() {
            @Override
            public void onSuccess() {
                Log.d("TAG", "onSuccess: ");
            }
            @Override
            public void onError(Exception e) {
                Log.d("TAG", "onError: " + e);
            }
        });
        holder.textName.setText(item.getName());
        holder.textDescription.setText(formatText(item.getDescription()));
//        holder.buttonDoNow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Xử lý sự kiện khi nút "Làm ngay" được nhấn
////                Log.d("TAG", "id category: " + item.getId());
//                Intent intent = new Intent(activity, ListCourse.class);
//                intent.putExtra("categoryId", item.getId());
//                activity.startActivity(intent);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    private String formatText(String text) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            CharSequence formattedText = Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY);
            return formattedText.toString();
        } else {
            CharSequence formattedText = Html.fromHtml(text);
            return formattedText.toString();
        }
    }
}



