package com.example.musicappth73nhom16.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.musicappth73nhom16.Model.Playlist;
import com.example.musicappth73nhom16.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PlaylistAdapter extends ArrayAdapter<Playlist> {

    public PlaylistAdapter(@NonNull Context context, int resource, @NonNull List<Playlist> objects) {
        super(context, resource, objects);
    }
    class ViewHolder {
        TextView playlistName;
        ImageView playlistbackground, playlistImg;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.playlist,null);
            viewHolder = new ViewHolder();
            viewHolder.playlistbackground = convertView.findViewById(R.id.imageviewbackgroundplaylist);
            viewHolder.playlistImg = convertView.findViewById(R.id.imageviewplaylist);
            viewHolder.playlistName = convertView.findViewById(R.id.textviewnameplaylist);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Playlist playlist = getItem(position);
        Picasso.with(getContext()).load(playlist.getHinhPlayList()).into(viewHolder.playlistbackground);
        Picasso.with(getContext()).load(playlist.getIcon()).into(viewHolder.playlistImg);
        viewHolder.playlistName.setText(playlist.getTen());

        return convertView;
    }
}

