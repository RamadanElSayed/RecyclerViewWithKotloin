package com.example.kotlinrecyclerview

import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitService {
    @GET("index/")
    fun getListData(): Observable<ArrayList<dataObject>>

    @GET("index/")
    fun getListData2(): Call<ArrayList<dataObject>>

}