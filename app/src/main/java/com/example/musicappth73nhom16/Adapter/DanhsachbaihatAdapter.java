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

import com.example.musicappth73nhom16.Model.BaiHat;
import com.example.musicappth73nhom16.R;

import java.util.ArrayList;

public class DanhsachbaihatAdapter extends RecyclerView.Adapter<DanhsachbaihatAdapter.ViewHolder> {
    Context context;
    ArrayList<BaiHat> arrBaiHat;

    public DanhsachbaihatAdapter(Context context, ArrayList<BaiHat> arrBaiHat) {
        this.context = context;
        this.arrBaiHat = arrBaiHat;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.danhsachbaihat,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BaiHat baiHat = arrBaiHat.get(position);
        holder.txtTenBH.setText(baiHat.getTenBaiHat());
        holder.txtTenCS.setText(baiHat.getCaSi());
        holder.txtIndex.setText(position + 1 +"");
    }

    @Override
    public int getItemCount() {
        return arrBaiHat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtIndex, txtTenBH, txtTenCS;
        ImageView imgLuotThich;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtIndex = itemView.findViewById(R.id.textViewDanhSachIndex);
            txtTenBH = itemView.findViewById(R.id.textViewTenBaiHat);
            txtTenCS = itemView.findViewById(R.id.textViewCaSi);
            imgLuotThich = itemView.findViewById(R.id.imageViewLuotThichDanhSachBaiHat);
            imgLuotThich.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PlayNhacActivity.class);
                    intent.putExtra("baihat", arrBaiHat.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
