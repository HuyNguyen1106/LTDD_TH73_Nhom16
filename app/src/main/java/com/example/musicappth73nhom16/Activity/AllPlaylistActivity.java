package com.example.musicappth73nhom16.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.musicappth73nhom16.Adapter.ListPlaylistsAdapter;
import com.example.musicappth73nhom16.Model.Playlist;
import com.example.musicappth73nhom16.R;
import com.example.musicappth73nhom16.Service.APIService;
import com.example.musicappth73nhom16.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllPlaylistActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView;
    ArrayList<Playlist> arrPlaylist;
    ListPlaylistsAdapter listPlaylistsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_playlist);
        anhxa();
        init();
        getData();
    }

    private void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("All Playlist");
        toolbar.setTitleTextColor(getResources().getColor(R.color.toolbarTitle));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void anhxa() {
        toolbar = findViewById(R.id.allPlaylistToolbar);
        recyclerView = findViewById(R.id.recyclerViewAllPlaylist);
    }

    private void getData() {
        DataService dataService = APIService.getService();
        Call<List<Playlist>> callback = dataService.GetDanhSachCacPlayList();
        callback.enqueue(new Callback<List<Playlist>>() {
            @Override
            public void onResponse(Call<List<Playlist>> call, Response<List<Playlist>> response) {
                arrPlaylist = (ArrayList<Playlist>) response.body();
                listPlaylistsAdapter = new ListPlaylistsAdapter(AllPlaylistActivity.this,arrPlaylist);
                recyclerView.setLayoutManager(new GridLayoutManager(AllPlaylistActivity.this,2));
                recyclerView.setAdapter(listPlaylistsAdapter);
            }

            @Override
            public void onFailure(Call<List<Playlist>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Load playlists fail....!",Toast.LENGTH_LONG).show();
            }
        });
    }
}

