package com.example.musicappth73nhom16.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.ImageView;

import com.example.musicappth73nhom16.Adapter.DanhsachbaihatAdapter;
import com.example.musicappth73nhom16.Model.*;

import com.example.musicappth73nhom16.R;
import com.example.musicappth73nhom16.Service.APIService;
import com.example.musicappth73nhom16.Service.DataService;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Url;

public class DanhSachBaiHatActivity extends AppCompatActivity {
    CoordinatorLayout coordinatorLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar toolbar;
    RecyclerView recyclerView;
    FloatingActionButton floatingActionButton;
    ImageView imgviewdanhsachbaihat;
    ArrayList<BaiHat> arrBaiHat;
    DanhsachbaihatAdapter danhsachbaihatAdapter;

    QuangCao quangcao;
    Playlist playlist;
    TheLoai theLoai;
    Album album;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_bai_hat);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        getIntentExtra();
        anhXa();
        Init();
        if (quangcao != null && !quangcao.getTenBaiHat().equals("")) {
            setValueInView(quangcao.getTenBaiHat(),quangcao.getHinhBaiHat());
            getDataQuangCao(quangcao.getIdQuangCao());
        }
        if (playlist != null && !playlist.getTen().equals("")) {
            setValueInView(playlist.getTen(),playlist.getHinhPlayList());
            getDataPlaylist(playlist.getIdPlayList());
        }
        if (theLoai != null && !theLoai.getTenTheLoai().equals("")) {
            setValueInView(theLoai.getTenTheLoai(),theLoai.getHinhTheLoai());
            getDataTheLoai(theLoai.getIdTheLoai());
        }
        if (album != null && !album.getTenAlbum().equals("")) {
            setValueInView(album.getTenAlbum(),album.getHinhAlbum());
            getDataAlbum(album.getIdAlbum());
        }
    }

    private void Init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitleTextColor(getResources().getColor(R.color.toolbarTitle));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
        floatingActionButton.setEnabled(false);
    }

    private void anhXa() {
        coordinatorLayout = findViewById(R.id.coordinator);
        collapsingToolbarLayout = findViewById(R.id.collapsingToolbar);
        toolbar = findViewById(R.id.toolbarDanhSach);
        recyclerView = findViewById(R.id.recycleViewDanhSachBaiHat);
        floatingActionButton = findViewById(R.id.floatingActionBtn);
        imgviewdanhsachbaihat = findViewById(R.id.imageViewDanhSachCaKhuc);
    }

    private void getDataAlbum(String idAlbum) {
        DataService dataService = APIService.getService();
        Call<List<BaiHat>> callback = dataService.GetDanhSachBaiHatTheoAlbum(idAlbum);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                arrBaiHat = (ArrayList<BaiHat>) response.body();
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhSachBaiHatActivity.this, arrBaiHat);
                recyclerView.setLayoutManager(new LinearLayoutManager(DanhSachBaiHatActivity.this));
                recyclerView.setAdapter(danhsachbaihatAdapter);
                eventClick();
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }

    private void getDataTheLoai(String idTheLoai) {
        DataService dataService = APIService.getService();
        Call<List<BaiHat>> callback = dataService.GetDanhSachBaiHatTheoTheLoai(idTheLoai);

        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                arrBaiHat = (ArrayList<BaiHat>) response.body();
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhSachBaiHatActivity.this, arrBaiHat);
                recyclerView.setLayoutManager(new LinearLayoutManager(DanhSachBaiHatActivity.this));
                recyclerView.setAdapter(danhsachbaihatAdapter);
                eventClick();
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }

    private void getDataPlaylist(String idPlayList) {
        DataService dataService = APIService.getService();
        Call<List<BaiHat>> callback = dataService.GetDanhSachBaiHatTheoPlayList(idPlayList);

        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                arrBaiHat = (ArrayList<BaiHat>) response.body();
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhSachBaiHatActivity.this, arrBaiHat);
                recyclerView.setLayoutManager(new LinearLayoutManager(DanhSachBaiHatActivity.this));
                recyclerView.setAdapter(danhsachbaihatAdapter);
                eventClick();
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }

    private void getDataQuangCao(String id) {
        DataService dataService = APIService.getService();
        Call<List<BaiHat>> callback = dataService.GetDanhSachBaiHatTheoQuangCao(id);

        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                arrBaiHat = (ArrayList<BaiHat>) response.body();
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhSachBaiHatActivity.this, arrBaiHat);
                recyclerView.setLayoutManager(new LinearLayoutManager(DanhSachBaiHatActivity.this));
                recyclerView.setAdapter(danhsachbaihatAdapter);
                eventClick();
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }

    private void setValueInView(String ten, String hinh) {
        collapsingToolbarLayout.setTitle(ten);
        try {
            URL url = new URL(hinh);
            Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(),bitmap);
            collapsingToolbarLayout.setBackground(bitmapDrawable);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Picasso.with(this).load(hinh).into(imgviewdanhsachbaihat);
    }

    private void getIntentExtra() {
        Intent intent = getIntent();
        if (intent != null){
            if(intent.hasExtra("banner")) {
                quangcao = (QuangCao) intent.getSerializableExtra("banner");
            }
            if (intent.hasExtra("itemPlaylist")) {
                playlist = (Playlist) intent.getSerializableExtra("itemPlaylist");
            }
            if (intent.hasExtra("idtheloai")) {
                theLoai = (TheLoai) intent.getSerializableExtra("idtheloai");
            }
            if (intent.hasExtra("album")) {
                album = (Album) intent.getSerializableExtra("album");
            }
        }
    }
    private void eventClick(){
        floatingActionButton.setEnabled(true);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DanhSachBaiHatActivity.this, PlayNhacActivity.class);
                intent.putExtra("cacbaihat", arrBaiHat);
                startActivity(intent);
            }
        });
    }
}
