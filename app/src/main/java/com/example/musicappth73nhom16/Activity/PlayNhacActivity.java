package com.example.musicappth73nhom16.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appmusic.Adapter.ViewPagerPlayListNhac;
import com.example.appmusic.Fragment.Fragment_DiaNhac;
import com.example.appmusic.Fragment.Fragment_Play_DanhSachCacBaiHat;
import com.example.appmusic.Model.BaiHat;
import com.example.musicappth73nhom16.R;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

public class PlayNhacActivity extends AppCompatActivity {

    Toolbar toolbarPlayNhac;
    TextView txtTimeSong,txtTotalTimeSong;
    SeekBar sktime;
    ImageButton imgPlay,imgRepeat,imgNext,imgPre,imgRandom;
    ViewPager viewPagerPlayNhac;
    public static ArrayList<BaiHat> mangBaiHat = new ArrayList<>();
    public static ViewPagerPlayListNhac adapterNhac;
    Fragment_DiaNhac fragment_diaNhac;
    Fragment_Play_DanhSachCacBaiHat fragment_play_danhSachCacBaiHat;
    MediaPlayer mediaPlayer;
    int position = 0;
    boolean repeat = false;
    boolean checkrandom = false;
    boolean next = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_nhac);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        GetDataFromIntent();
        init();
        EventClick();
    }

    private void EventClick() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (adapterNhac.getItem(1) != null){
                    if (mangBaiHat.size() > 0){
                        fragment_diaNhac.Playnhac(mangBaiHat.get(0).getHinhBaiHat());
                        handler.removeCallbacks(this);
                    }else {
                        handler.postDelayed(this,300);
                    }
                }
            }
        },500);
        imgPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    imgPlay.setImageResource(R.drawable.iconplay);
                }else {
                    mediaPlayer.start();
                    imgPlay.setImageResource(R.drawable.iconpause);
                }
            }
        });
        imgRepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (repeat == false){
                   if (checkrandom == true){
                       checkrandom = false;
                       imgRepeat.setImageResource(R.drawable.iconsyned);
                       imgRandom.setImageResource(R.drawable.iconsuffle);
                   }
                   imgRepeat.setImageResource(R.drawable.iconsyned);
                   repeat = true;
                }else{
                    imgRepeat.setImageResource(R.drawable.iconrepeat);
                    repeat = false;
                }
            }
        });
        imgRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkrandom == false){
                    if (repeat == true){
                        repeat = false;
                        imgRandom.setImageResource(R.drawable.iconshuffled);
                        imgRepeat.setImageResource(R.drawable.iconrepeat);
                    }
                    imgRandom.setImageResource(R.drawable.iconshuffled);
                    checkrandom = true;
                }else{
                    imgRandom.setImageResource(R.drawable.iconsuffle);
                    checkrandom = false;
                }
            }
        });
        sktime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
            }
        });
        imgNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mangBaiHat.size() >0){
                    if (mediaPlayer.isPlaying() || mediaPlayer != null){
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if (position < (mangBaiHat.size())){
                        imgPlay.setImageResource(R.drawable.iconpause);
                        position++;
                        if (repeat == true){
                            if (position == 0){
                                position = mangBaiHat.size();
                            }
                            position -= 1;
                        }
                        if ( checkrandom == true){
                            Random random = new Random();
                            int index = random.nextInt(mangBaiHat.size());
                            if (index == position){
                                position = index - 1;
                            }
                            position = index;
                        }
                        if (position > (mangBaiHat.size()-1)){
                            position = 0;
                        }
                        new PlayMp3().execute(mangBaiHat.get(position).getLinkBaiHat());
                        fragment_diaNhac.Playnhac(mangBaiHat.get(position).getHinhBaiHat());
                        getSupportActionBar().setTitle(mangBaiHat.get(position).getTenBaiHat());
                        UpdateTime();
                    }
                }
                imgPre.setClickable(false);
                imgPre.setClickable(false);
                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgPre.setClickable(true);
                        imgPre.setClickable(true);
                    }
                }, 5000);
            }
        });
        imgPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mangBaiHat.size() >0){
                    if (mediaPlayer.isPlaying() || mediaPlayer != null){
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if (position < (mangBaiHat.size())){
                        imgPlay.setImageResource(R.drawable.iconpause);
                        position--;

                        if (position < 0 ){
                            position = mangBaiHat.size() - 1;
                        }
                        if (repeat == true){
                            position += 1;
                        }
                        if ( checkrandom == true){
                            Random random = new Random();
                            int index = random.nextInt(mangBaiHat.size());
                            if (index == position){
                                position = index - 1;
                            }
                            position = index;
                        }
                        new PlayMp3().execute(mangBaiHat.get(position).getLinkBaiHat());
                        fragment_diaNhac.Playnhac(mangBaiHat.get(position).getHinhBaiHat());
                        getSupportActionBar().setTitle(mangBaiHat.get(position).getTenBaiHat());
                        UpdateTime();
                    }
                }
                imgPre.setClickable(false);
                imgPre.setClickable(false);
                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgPre.setClickable(true);
                        imgPre.setClickable(true);
                    }
                }, 5000);
            }
        });
    }

    private void GetDataFromIntent() {
        Intent intent = getIntent();
        mangBaiHat.clear();
        if (intent != null){
            if (intent.hasExtra("cakhuc")) {
                BaiHat baiHat = intent.getParcelableExtra("cakhuc");
                mangBaiHat.add(baiHat);
            }
            if (intent.hasExtra("cacbaihat")) {
                ArrayList<BaiHat> baiHatArrayList = intent.getParcelableArrayListExtra("cacbaihat");
                mangBaiHat = baiHatArrayList;
            }
        }
    }

    private void init() {
        toolbarPlayNhac = findViewById(R.id.toolbarplaynhac);
        txtTimeSong = findViewById(R.id.textviewtimesong);
        txtTotalTimeSong = findViewById(R.id.textviewtotaltimesong);
        sktime = findViewById(R.id.seekbarsong);
        imgPlay = findViewById(R.id.imagebuttonplay);
        imgRepeat = findViewById(R.id.imagebuttonrepeat);
        imgNext = findViewById(R.id.imagebuttonnext);
        imgPre = findViewById(R.id.imagebuttonpre);
        imgRandom = findViewById(R.id.imagebuttonsuffle);
        viewPagerPlayNhac = findViewById(R.id.viewpagerplaynhac);
        setSupportActionBar(toolbarPlayNhac);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarPlayNhac.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                mediaPlayer.stop();
                mangBaiHat.clear();
            }
        });
        toolbarPlayNhac.setTitleTextColor(Color.WHITE);
        fragment_diaNhac = new Fragment_DiaNhac();
        fragment_play_danhSachCacBaiHat = new Fragment_Play_DanhSachCacBaiHat();
        adapterNhac = new ViewPagerPlayListNhac(getSupportFragmentManager());
        adapterNhac.AddFragment(fragment_play_danhSachCacBaiHat);
        adapterNhac.AddFragment(fragment_diaNhac);
        viewPagerPlayNhac.setAdapter(adapterNhac);
        fragment_diaNhac = (Fragment_DiaNhac) adapterNhac.getItem(1);
        if (mangBaiHat.size() > 0){
            getSupportActionBar().setTitle(mangBaiHat.get(0).getTenBaiHat());
            new PlayMp3().execute(mangBaiHat.get(0).getLinkBaiHat());
            imgPlay.setImageResource(R.drawable.iconpause);
        }
    }

    class PlayMp3 extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            return strings[0];
        }

        @Override
        protected void onPostExecute(String baiHat) {
            super.onPostExecute(baiHat);
            try {
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mediaPlayer.stop();
                        mediaPlayer.reset();
                    }
                });
            mediaPlayer.setDataSource(baiHat);
            mediaPlayer.prepare();

            } catch (IOException e) {
                e.printStackTrace();
            }
            mediaPlayer.start();
            TimeSong();
            UpdateTime();
        }
    }

    private void TimeSong() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        txtTotalTimeSong.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
        sktime.setMax(mediaPlayer.getDuration());
    }
    private void UpdateTime(){
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer != null){
                    sktime.setProgress(mediaPlayer.getCurrentPosition());
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                    txtTimeSong.setText(simpleDateFormat.format(mediaPlayer.getCurrentPosition()));
                    handler.postDelayed(this,300);
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            next = true;
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        },300);
        final Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (next == true) {
                    if (position < (mangBaiHat.size())) {
                        imgPlay.setImageResource(R.drawable.iconpause);
                        position++;
                        if (repeat == true) {
                            if (position == 0) {
                                position = mangBaiHat.size();
                            }
                            position -= 1;
                        }
                        if (checkrandom == true) {
                            Random random = new Random();
                            int index = random.nextInt(mangBaiHat.size());
                            if (index == position) {
                                position = index - 1;
                            }
                            position = index;
                        }
                        if (position > (mangBaiHat.size() - 1)) {
                            position = 0;
                        }
                        new PlayMp3().execute(mangBaiHat.get(position).getLinkBaiHat());
                        fragment_diaNhac.Playnhac(mangBaiHat.get(position).getHinhBaiHat());
                        getSupportActionBar().setTitle(mangBaiHat.get(position).getTenBaiHat());
                        }
                imgPre.setClickable(false);
                imgPre.setClickable(false);
                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgPre.setClickable(true);
                        imgPre.setClickable(true);
                    }
                }, 5000);
                next = false;
                handler1.removeCallbacks(this);
                }else{
                    handler1.postDelayed(this,1000);
                }
            }
        },1000);
    }
}
