package com.example.faatak_androidapp_saurabhmulgaonkar.Interface;

import com.example.faatak_androidapp_saurabhmulgaonkar.Pojo.Main;
import com.example.faatak_androidapp_saurabhmulgaonkar.Pojo.SingleNot;
import com.example.faatak_androidapp_saurabhmulgaonkar.SingleNotice;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("noticebysocietyid/1")
    Call<Main> getNotice();

    @GET("notice/noticebynoticeid/{id}")
    Call<SingleNot> getSingleNotice(@Path("id") int id);
}
