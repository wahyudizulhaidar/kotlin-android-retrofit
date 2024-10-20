package com.wahyuzul.androidretrofit

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import com.wahyuzul.androidretrofit.retrofit.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {

    private val TAG: String = "MainActivity"

    // Inisialisasi ApiService karena endPoint bukan companion object
    private val apiService = ApiService()
    private val endPoint = apiService.endPoint

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        getDataFromAPi()
    }

    private fun getDataFromAPi() {
        endPoint.getPhotos()
            .enqueue(object : Callback<List<MainModel>> {
                override fun onResponse(call: Call<List<MainModel>>, response : Response<List<MainModel>>) {
                    if (response.isSuccessful) {
                        val result = response.body()
                        printLog(result.toString())
                        showPhotos(result!!)
                    }
                }

                override fun onFailure(call: Call<List<MainModel>>, t: Throwable) {
                    printLog( t.toString() )
                }
            })
    }

    private fun printLog(message: String) {
        Log.d(TAG, message)
    }

    private fun showPhotos(photos: List<MainModel>) {
        for (photo in photos) {
            printLog("title: ${photo.title}")
        }
    }
}