# LTDD_TH73_Nhom16
Danh sách thành viên:
- Nguyễn Khắc Vũ, MSSV:1751010182
- Hoàng Thị Quyên, MSSV: 1751010121
- Nguyễn Chung Huy, MSSV: 1751010044
- Đỗ Phú Khương
- Phạm Thanh Tùng

Phân công:
  - Vũ: 
    + fragment Tìm kiếm; 
    + adapter: PlayMusicAdapter; 
    + Activity: PlayMusic
    +API(php):PlayListForCurrentDay.php, SearchBaiHat.php, SongBanner.php, TatCaAlbum.php, TatCaChuDe.php, TheLoaiTheoChuDe.php ,                     UpdateLuotThich.php
    + Thiết kế và UpLoad Cơ sở dữ liệu
  - Quyên: 
    + fragment Banner; 
    + model QuangCao; 
    + adapter: BannerAdapter
    + API(php):AlbumHot.php, BaiHatDuocThich.php, ChuDeVaTheLoaiTrongNgay.php, Connect.php, DanhSachBaiHat.php,DanhSachCacPlayList.php
    + package Service:DataService
    + Thiết kế và UpLoad Cơ sở dữ liệu( hình ảnh, bài hát)
  - Tùng: 
    + fragment Chủ đề thể loại; 
    + model ChuDe,TheLoai; 
    + adapter: TatCaChuDeAdapter, TheLoaiTheoChuDeAdapter; 
    + Activity: TatCaChuDe, TheLoaiTheoChuDe
  - Khương 
    + fragment: Album hot, Hot songs; 
    + model: Album,BaiHat; 
    + Adapter: AlbumAdapter, AllAlbumAdapter, BaiHatHotAdapter; 
    + Activity: AllAlbum
  - Huy: 
    + fragment Playlist, Danh_Sach_Bai_Hat_Play; 
    + model Playlist; 
    + adapter: PlaylistAdapter, DanhsachbaihatAdapter, ListPlaylistAdapter, MainViewPagerAdapter, ViewPagerPlaylistMusic; 
    + Activity: AllPlaylist, Danhsachbaihat
