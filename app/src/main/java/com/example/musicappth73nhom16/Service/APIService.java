package com.example.musicappth73nhom16.Service;

public class APIService {
    private static String base_url = "https://app-music-th73.000webhostapp.com/Server/";
    public  static DataService getService(){
        return APIRetrofitClient.getClient(base_url).create(DataService.class);
    }
}
