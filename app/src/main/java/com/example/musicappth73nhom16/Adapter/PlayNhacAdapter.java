package com.example.musicappth73nhom16.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmusic.Model.BaiHat;
import com.example.musicappth73nhom16.R;

import java.util.ArrayList;

public class PlayNhacAdapter extends  RecyclerView.Adapter<PlayNhacAdapter.ViewHolder>{

    Context context;
    ArrayList<BaiHat> mangBaiHat;

    public PlayNhacAdapter(Context context, ArrayList<BaiHat> mangBaiHat) {
        this.context = context;
        this.mangBaiHat = mangBaiHat;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_playbaihat,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BaiHat baiHat = mangBaiHat.get(position);
        holder.txtCaSi.setText(baiHat.getCaSi());
        holder.txtIndex.setText(position + 1 + "");
        holder.txtTenBaiHat.setText(baiHat.getTenBaiHat());

    }

    @Override
    public int getItemCount() {
        return mangBaiHat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtIndex, txtTenBaiHat,txtCaSi;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtCaSi = itemView.findViewById(R.id.textviewplaynhactencasi);
            txtIndex = itemView.findViewById(R.id.textviewplaynhacindex);
            txtTenBaiHat = itemView.findViewById(R.id.textviewplaynhactenbaihat);
        }
    }
}
