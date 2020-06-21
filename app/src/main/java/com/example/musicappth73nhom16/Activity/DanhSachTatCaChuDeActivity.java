package com.example.musicappth73nhom16.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.musicappth73nhom16.Adapter.DanhSachTatCaChuDeAdapter;
import com.example.musicappth73nhom16.Model.ChuDe;
import com.example.musicappth73nhom16.R;
import com.example.musicappth73nhom16.Service.APIService;
import com.example.musicappth73nhom16.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachTatCaChuDeActivity extends AppCompatActivity {

    RecyclerView recyclerViewTatCaChuDe;
    Toolbar toolbarTatCaChuDe;
    DanhSachTatCaChuDeAdapter danhSachTatCaChuDeAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_tat_ca_chu_de);
        init();
        GetData();
    }

    private void GetData() {
        DataService dataService = APIService.getService();
        Call<List<ChuDe>> callback = dataService.GetTatCaChuDe();
        callback.enqueue(new Callback<List<ChuDe>>() {
            @Override
            public void onResponse(Call<List<ChuDe>> call, Response<List<ChuDe>> response) {
                ArrayList<ChuDe> mangchude = (ArrayList<ChuDe>) response.body();
                danhSachTatCaChuDeAdapter = new DanhSachTatCaChuDeAdapter(DanhSachTatCaChuDeActivity.this,mangchude);
                recyclerViewTatCaChuDe.setLayoutManager(new GridLayoutManager(DanhSachTatCaChuDeActivity.this,1));
                recyclerViewTatCaChuDe.setAdapter(danhSachTatCaChuDeAdapter);
            }

            @Override
            public void onFailure(Call<List<ChuDe>> call, Throwable t) {

            }
        });
    }

    private void init() {
        recyclerViewTatCaChuDe =findViewById(R.id.recyclerviewallchude);
        toolbarTatCaChuDe = findViewById(R.id.toolbarallchude);
        setSupportActionBar(toolbarTatCaChuDe);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tất Cả Chủ Đề");
        toolbarTatCaChuDe.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
