package com.example.mytask

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var myRecyclerView: RecyclerView
    private lateinit var myAdapter: MyAdapter
    private val BASE_URL = "https://dl.dropboxusercontent.com/s/p57gxwqm84zkp96/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_home)

        myRecyclerView = findViewById(R.id.recyclerView)
        myRecyclerView.layoutManager = LinearLayoutManager(this)

        getAllData()
    }

    private fun getAllData() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

        val retroData = retrofit.getData()
        retroData.enqueue(object : Callback<List<Result>> {
            override fun onResponse(call: Call<List<Result>>, response: Response<List<Result>>) {
                response.body()?.let { data ->
                    myAdapter = MyAdapter(this@MainActivity, data)
                    myRecyclerView.setAdapter(myAdapter)
                    Log.d("data", data.toString())
                } ?: Log.e("Response Error", "Response body is null")
            }

            override fun onFailure(call: Call<List<Result>>, t: Throwable) {
                Log.e("API Error", "Failed to fetch data: ${t.message}")
            }
        })
    }
}
