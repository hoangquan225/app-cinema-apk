package com.example.appcinema.page.listFilm;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.appcinema.R;

public class CustomViewHolder extends RecyclerView.ViewHolder {
    public  ImageView imageView;
    public TextView textName;
    public TextView textDescription;
    public Button btnBook;

    public CustomViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.imageView);
        textName = itemView.findViewById(R.id.textName);
        textDescription = itemView.findViewById(R.id.textDescription);
        btnBook = itemView.findViewById(R.id.btnBook);
    }
}

