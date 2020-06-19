# LTDD_TH73_Nhom16
Danh sách thành viên:
- Nguyễn Khắc Vũ, MSSV:1751010182
- Hoàng Thị Quyên, MSSV: 1751010121
- Nguyễn Chung Huy, MSSV: 1751010044
- Đỗ Phú Khương
- Phạm Thanh Tùng


-Link:API,Hình Ảnh
  + https://vn.000webhost.com/members/website/app-music-th73/dashboard
  (Vào Quản Lý File-->Tải file lên)
  +Thông tin tài khoản:
    Email: khacvu0505@gmail.com
    Mật khẩu: 01699652356
  

Phân công:
  - Vũ: 
    + Fragment: Tìm kiếm
    + Adapter: PlayMusic
    + Activity: PlayMusic
    + API(php):PlayListForCurrentDay.php, SearchBaiHat.php, SongBanner.php, TatCaAlbum.php, TatCaChuDe.php, TheLoaiTheoChuDe.php ,                     UpdateLuotThich.php
    + Thiết kế và UpLoad Cơ sở dữ liệu
  - Quyên: 
    + Fragment: Banner
    + Model: QuangCao
    + Adapter: Banner
    + API(php):AlbumHot.php, BaiHatDuocThich.php, ChuDeVaTheLoaiTrongNgay.php, Connect.php, DanhSachBaiHat.php,DanhSachCacPlayList.php
    + package Service: DataService
    + Thiết kế và UpLoad Cơ sở dữ liệu( hình ảnh, bài hát)
  - Tùng: 
    + Fragment: Chủ đề thể loại
    + Model: ChuDe,TheLoai
    + Adapter: TatCaChuDeAdapter, TheLoaiTheoChuDeAdapter
    + Activity: TatCaChuDe, TheLoaiTheoChuDe
  - Khương 
    + Fragment: Album hot, Hot songs
    + Model: Album,BaiHat
    + Adapter: AlbumAdapter, AllAlbumAdapter, BaiHatHotAdapter
    + Activity: AllAlbum
  - Huy: 
    + Fragment: Playlist, Danh_Sach_Bai_Hat_Play
    + Model: Playlist
    + Adapter: PlaylistAdapter, DanhsachbaihatAdapter, ListPlaylistAdapter, MainViewPagerAdapter, ViewPagerPlaylistMusic
    + Activity: AllPlaylist, Danhsachbaihat
