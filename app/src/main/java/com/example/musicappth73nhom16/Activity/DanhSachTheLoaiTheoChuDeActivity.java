package com.example.musicappth73nhom16.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import com.example.musicappth73nhom16.Adapter.DanhSachTheLoaiTheoChuDeAdapter;
import com.example.musicappth73nhom16.Model.ChuDe;
import com.example.musicappth73nhom16.Model.TheLoai;
import com.example.musicappth73nhom16.R;
import com.example.musicappth73nhom16.Service.APIService;
import com.example.musicappth73nhom16.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachTheLoaiTheoChuDeActivity extends AppCompatActivity {
    ChuDe chuDe;
    RecyclerView recyclerViewTheLoaiTheoChuDe;
    Toolbar toolbarTheLoaiTheoChuDe;
    DanhSachTheLoaiTheoChuDeAdapter danhSachTheLoaiTheoChuDeAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_the_loai_theo_chu_de);
        GetIntent();
        init();
        GetData();
    }

    private void GetData() {
        DataService dataService= APIService.getService();
        Call<List<TheLoai>> callback=dataService.GetTheLoaiTheoChuDe(chuDe.getIdChuDe());
        callback.enqueue(new Callback<List<TheLoai>>() {
            @Override
            public void onResponse(Call<List<TheLoai>> call, Response<List<TheLoai>> response) {
                ArrayList<TheLoai> mangtheloai= (ArrayList<TheLoai>) response.body();
                danhSachTheLoaiTheoChuDeAdapter=new DanhSachTheLoaiTheoChuDeAdapter(DanhSachTheLoaiTheoChuDeActivity.this,mangtheloai);
                recyclerViewTheLoaiTheoChuDe.setLayoutManager(new GridLayoutManager(DanhSachTheLoaiTheoChuDeActivity.this,2));
                recyclerViewTheLoaiTheoChuDe.setAdapter(danhSachTheLoaiTheoChuDeAdapter);
            }

            @Override
            public void onFailure(Call<List<TheLoai>> call, Throwable t) {

            }
        });
    }

    private void init() {
        recyclerViewTheLoaiTheoChuDe=findViewById(R.id.recyclerviewtheloaitheochude);
        toolbarTheLoaiTheoChuDe=findViewById(R.id.toolbartheloaitheochude);
        setSupportActionBar(toolbarTheLoaiTheoChuDe);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(chuDe.getTenChuDe());
       toolbarTheLoaiTheoChuDe.setNavigationOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               finish();
           }
       });
    }

    private void GetIntent() {
        Intent intent=getIntent();
        if(intent.hasExtra("chude")){
            chuDe= (ChuDe) intent.getSerializableExtra("chude");

        }
    }
}
