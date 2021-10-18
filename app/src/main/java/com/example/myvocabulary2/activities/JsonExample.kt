package com.example.myvocabulary2.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myvocabulary2.R
import com.example.myvocabulary2.adapters.MainAdapter
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_json_example.*
import okhttp3.*
import java.io.IOException

class JsonExample : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_json_example)

        recyclerView_main.layoutManager = LinearLayoutManager(this)
//        recyclerView_main.adapter = MainAdapter()

        fetchJson()
    }

    fun fetchJson() {
        println("Attempting to Fetch JSON")
        val url = "https://api.letsbuildthatapp.com/youtube/home_feed"

        val request = Request.Builder().url(url).build()

        val client = OkHttpClient()
        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call, response: Response) {
                val body = response.body()?.string()
                println(body)

                val gson = GsonBuilder().create()

                val homeFeed = gson.fromJson(body, HomeFeed::class.java)

                runOnUiThread {
//                    for(i in MainAdapter(homeFeed))
                    recyclerView_main.adapter = MainAdapter(homeFeed)
                }
            }

            override fun onFailure(call: Call?, e: IOException) {
                print("Failed to execute")
            }
        })
    }
}

class HomeFeed(val videos: List<Video>)

class Video(val id: Int, val name: String, val link: String,
            val imageUrl: String, numberOfViews: Int, val channel: Channel)

class Channel(val name: String, val profileImageUrl: String)