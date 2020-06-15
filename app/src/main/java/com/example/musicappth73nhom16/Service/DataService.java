package com.example.musicappth73nhom16.Service;

import com.example.musicappth73nhom16.Model.Album;
import com.example.musicappth73nhom16.Model.BaiHat;
import com.example.musicappth73nhom16.Model.ChuDe;
import com.example.musicappth73nhom16.Model.PlayList;
import com.example.musicappth73nhom16.Model.QuangCao;
import com.example.musicappth73nhom16.Model.TheLoai;
import com.example.musicappth73nhom16.Model.TheLoaiTrongNgay;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface DataService {
    @GET("SongBanner.php")
    Call<List<QuangCao>> GetDataBanner();

    @GET("PlayListForCurrentDay.php")
    Call<List<PlayList>> GetPlayListCurrentDay();

    @GET("chudevatheloaitrongngay.php")
    Call<TheLoaiTrongNgay> GetCategoryMusic();

    @GET("AlbumHot.php")
    Call<List<Album>> GetAlbumHot();

    @GET("BaiHatDuocThich.php")
    Call<List<BaiHat>> GetBaiHatHot();

    @FormUrlEncoded
    @POST("DanhSachBaiHat.php")
    Call<List<BaiHat>> GetDanhSachBaiHatTheoQuangCao(@Field("idquangcao") String idquangcao);

    @FormUrlEncoded
    @POST("DanhSachBaiHat.php")
    Call<List<BaiHat>> GetDanhSachBaiHatTheoPlayList(@Field("idplaylist") String idplaylist);

    @GET("DanhSachCacPlayList.php")
    Call<List<PlayList>> GetDanhSachCacPlayList();

    @FormUrlEncoded
    @POST("DanhSachBaiHat.php")
    Call<List<BaiHat>> GetDanhSachBaiHatTheoTheLoai(@Field("idtheloai") String idtheloai);

    @GET("TatCaChuDe.php")
    Call<List<ChuDe>> GetTatCaChuDe();

    @FormUrlEncoded
    @POST("TheLoaiTheoChuDe.php")
    Call<List<TheLoai>> GetTheLoaiTheoChuDe(@Field("idchude") String idchude);

    @GET("TatCaAlbum.php")
    Call<List<Album>> GetAlbum();

    @FormUrlEncoded
    @POST("DanhSachBaiHat.php")
    Call<List<BaiHat>> GetDanhSachBaiHatTheoAlbum(@Field("idalbum") String idalbum);

    @FormUrlEncoded
    @POST("UpdateLuotThich.php")
    Call<String> UpdateLuotThich(@Field("luotthich") String luotthich, @Field("idbaihat") String idbaihat);

    @FormUrlEncoded
    @POST("SearchBaiHat.php")
    Call<List<BaiHat>> GetSearchBaiHat(@Field("tukhoa") String tukhoa);

}

