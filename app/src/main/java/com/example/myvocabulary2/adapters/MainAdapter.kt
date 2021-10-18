package com.example.myvocabulary2.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myvocabulary2.R
import com.example.myvocabulary2.activities.HomeFeed
import kotlinx.android.synthetic.main.video_row.view.*

class MainAdapter(val homeFeed: HomeFeed): RecyclerView.Adapter<CustomViewHolder>() {
//    val videoTitles = listOf("First title", "Second", "3rd", "MOOOORE Title")

    override fun getItemCount(): Int {
        print("auuuuuuuuuuuuu ${homeFeed.videos.count()}")
        return homeFeed.videos.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellForRow = layoutInflater.inflate(R.layout.video_row, parent, false)
        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val video = homeFeed.videos.get(position)
        print("video ${position}")
        holder?.view?.textView_video_title?.text = video.name
    }
}

class CustomViewHolder(val view: View): RecyclerView.ViewHolder(view){

}