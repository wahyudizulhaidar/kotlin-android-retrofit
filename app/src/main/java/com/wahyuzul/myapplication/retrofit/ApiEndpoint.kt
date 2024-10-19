package com.wahyuzul.myapplication.retrofit

import retrofit2.http.GET

interface ApiEndpoint {

    @GET( value = "photos")
    fun getPhotos()
}