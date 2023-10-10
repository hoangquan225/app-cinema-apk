package com.example.appcinema.page.home;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.example.appcinema.R;
import com.example.appcinema.models.Film;
import com.example.appcinema.page.listFilm.ListFilm;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomViewHolder> {
    private List<Film> itemList;
    private Context activity;

    public CustomAdapter(List<Film> itemList, FragmentActivity activity) {
        this.itemList = itemList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        Film item = itemList.get(position);

        Picasso.get().load(item.getThumbnail()).into(holder.imageView);
        holder.textName.setText(item.getName());
//        holder.textDescription.setText(formatText(item.getDescription()));
        holder.buttonDoNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xử lý sự kiện khi nút "Xem ngay" được nhấn
//                Log.d("TAG", "id category: " + item.getId());
                Intent intent = new Intent(activity, ListFilm.class);
                intent.putExtra("filmId", item.get_id());
                intent.putExtra("name", item.getName());
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
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

