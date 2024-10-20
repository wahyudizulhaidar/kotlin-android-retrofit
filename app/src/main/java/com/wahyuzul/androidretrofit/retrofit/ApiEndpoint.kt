package com.wahyuzul.androidretrofit.retrofit

import com.wahyuzul.androidretrofit.MainModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiEndpoint {

    @GET( value = "photos")
    fun getPhotos(): Call<List<MainModel>>
}