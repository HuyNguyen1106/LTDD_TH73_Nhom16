package com.example.musicappth73nhom16.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.musicappth73nhom16.Activity.AllPlaylistActivity;
import com.example.musicappth73nhom16.Activity.DanhSachBaiHatActivity;
import com.example.musicappth73nhom16.Adapter.PlaylistAdapter;
import com.example.musicappth73nhom16.Model.Playlist;
import com.example.musicappth73nhom16.R;
import com.example.musicappth73nhom16.Service.APIService;
import com.example.musicappth73nhom16.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Playlist extends Fragment {
    View view;
    ArrayList<Playlist> arrPlaylist;
    PlaylistAdapter playlistAdapter;
    ListView listView;
    TextView txtMore, txtTitlePlaylist;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_playlist,container,false);
        listView = view.findViewById(R.id.listviewplaylist);
        txtMore = view.findViewById(R.id.textviewmoreplaylist);
        txtTitlePlaylist = view.findViewById(R.id.textviewtitleplaylist);
        getData();
        txtMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AllPlaylistActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    private void getData() {
        DataService dataService = APIService.getService();
        Call<List<Playlist>> callback = dataService.GetPlayListCurrentDay();
        callback.enqueue(new Callback<List<Playlist>>() {
            @Override
            public void onResponse(Call<List<Playlist>> call, Response<List<Playlist>> response) {
                arrPlaylist = (ArrayList<Playlist>) response.body();
                playlistAdapter = new PlaylistAdapter(getActivity(),android.R.layout.simple_list_item_1,arrPlaylist);
                listView.setAdapter(playlistAdapter);
                setListViewHeightBasedOnChildren(listView);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(getActivity(), DanhSachBaiHatActivity.class);
                        intent.putExtra("itemPlaylist",arrPlaylist.get(position));
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onFailure(Call<List<Playlist>> call, Throwable t) {

            }
        });
    }
    public static void setListViewHeightBasedOnChildren(ListView listView) {

        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, View.MeasureSpec.UNSPECIFIED);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }
}

