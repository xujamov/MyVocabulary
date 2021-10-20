package com.example.myvocabulary2.activities

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myvocabulary2.R
import com.example.myvocabulary2.adapters.MainAdapter
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.annotations.SerializedName
import kotlinx.android.synthetic.main.activity_json_example.*
import okhttp3.*
import java.io.IOException

class JsonExample : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_json_example)

        recyclerView_main.layoutManager = LinearLayoutManager(this)

        fetchJson()
    }

    fun fetchJson() {
        println("MyLog: Attempting to Fetch JSON")
        val url = "http://10.0.2.2:9000/user"
        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()
        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call, response: Response) {
                val body = "{\"users\":" + response.body()?.string() + "}"

                val gson = Gson()
                val user = gson.fromJson(body, UserList::class.java)

                runOnUiThread {
                    recyclerView_main.adapter = MainAdapter(user.users)
                }
            }

            override fun onFailure(call: Call?, e: IOException) {
                println("MyLog: Failed to execute")
            }
        })
    }
}

class UserList(val users: List<User>)
data class User(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String
)
