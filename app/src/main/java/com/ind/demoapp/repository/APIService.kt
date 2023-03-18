package com.ind.demoapp.repository

import com.ind.demoapp.model.ItemInfoResponse
import retrofit2.Call
import retrofit2.http.GET



interface APIService {

    @GET("photos")
    fun makeRequest(): Call<List<ItemInfoResponse>>
}