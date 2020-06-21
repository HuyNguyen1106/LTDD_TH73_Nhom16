package com.example.musicappth73nhom16.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicappth73nhom16.Activity.DanhSachBaiHatActivity;
import com.example.musicappth73nhom16.Model.Album;
import com.example.musicappth73nhom16.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder> {
    Context context;
    ArrayList<Album> mangAlbum;

    public AlbumAdapter(Context context, ArrayList<Album> mangAlbum) {
        this.context = context;
        this.mangAlbum = mangAlbum;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_album,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Album album = mangAlbum.get(position);
        holder.txtCaSiAlbum.setText(album.getTenCaSiAlbum());
        holder.txtTenAlBum.setText(album.getTenAlbum());
        Picasso.with(context).load(album.getHinhAlbum()).into(holder.imgHinhAlbum);

    }

    @Override
    public int getItemCount() {

        return mangAlbum.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgHinhAlbum;
        TextView txtTenAlBum,txtCaSiAlbum;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgHinhAlbum = itemView.findViewById(R.id.imageviewalbum);
            txtTenAlBum = itemView.findViewById(R.id.textviewtenalbum);
            txtCaSiAlbum = itemView.findViewById(R.id.textviewtencasialbum);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DanhSachBaiHatActivity.class);
                    intent.putExtra("album",mangAlbum.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
