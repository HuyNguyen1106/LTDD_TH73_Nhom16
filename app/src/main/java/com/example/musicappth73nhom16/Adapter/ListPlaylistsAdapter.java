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
import com.example.musicappth73nhom16.Model.Playlist;
import com.example.musicappth73nhom16.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ListPlaylistsAdapter extends RecyclerView.Adapter<ListPlaylistsAdapter.ViewHolder> {
    Context context;
    ArrayList<Playlist> arrPlaylist;

    public ListPlaylistsAdapter(Context context, ArrayList<Playlist> arrPlaylist) {
        this.context = context;
        this.arrPlaylist = arrPlaylist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.playlists, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Playlist playlist = arrPlaylist.get(position);
        holder.txtListPlaylists.setText(playlist.getTen());
        Picasso.with(context).load(playlist.getHinhPlayList()).into(holder.imgListPlaylists);
    }

    @Override
    public int getItemCount() {
        return arrPlaylist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtListPlaylists;
        ImageView imgListPlaylists;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtListPlaylists = itemView.findViewById(R.id.textViewListPlaylists);
            imgListPlaylists = itemView.findViewById(R.id.imageViewListPlaylists);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DanhSachBaiHatActivity.class);
                    intent.putExtra("itemPlaylist",arrPlaylist.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}

