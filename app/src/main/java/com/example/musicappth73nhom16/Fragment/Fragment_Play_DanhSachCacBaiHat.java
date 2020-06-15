package com.example.musicappth73nhom16.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicappth73nhom16.R;

public class Fragment_Play_DanhSachCacBaiHat extends Fragment {
    View view;
    RecyclerView recyclerView;
    PlayNhacAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_play_danh_sach_cac_bai_hat, container, false);
        recyclerView = view.findViewById(R.id.recyclerViewDSBaiHatPlay);
        if (PlayNhacActivity.mangBaiHat.size() > 0){
            adapter = new PlayNhacAdapter(getActivity(),PlayNhacActivity.mangBaiHat);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(adapter);
        }
        return view;
    }
}
