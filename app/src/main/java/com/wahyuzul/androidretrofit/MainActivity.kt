package com.wahyuzul.androidretrofit

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.recyclerview.widget.LinearLayoutManager
import com.wahyuzul.androidretrofit.databinding.ActivityMainBinding
import com.wahyuzul.androidretrofit.retrofit.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {

    private val TAG: String = "MainActivity"
    private lateinit var binding : ActivityMainBinding
    lateinit var mainAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        setupRecylerView()
        getDataFromAPi()
    }

    private fun setupRecylerView() {
        mainAdapter = MainAdapter(arrayListOf())
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = mainAdapter
        }
    }

    private fun getDataFromAPi() {
        binding.progressBar.visibility = View.VISIBLE
        ApiService.endPoint.getData()
            .enqueue(object : Callback<MainModel> {
                override fun onResponse(
                    call: Call<MainModel>,
                    response : Response<MainModel>
                ) {
                    if (response.isSuccessful) {
                        binding.progressBar.visibility = View.GONE
                        showData(response.body()!!)
                    }
                }

                override fun onFailure(
                    call: Call<MainModel>,
                    t: Throwable
                ) {
                    binding.progressBar.visibility = View.GONE
                    printLog( "onFailure: $t" )
                }
            })
    }

    private fun printLog(message: String) {
        Log.d(TAG, message)
    }

    private fun showData(data: MainModel) {
        val results = data.result
        mainAdapter.setData( results )
    }
}