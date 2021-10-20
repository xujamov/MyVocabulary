package com.example.myvocabulary2.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myvocabulary2.R
import com.example.myvocabulary2.activities.User
import com.example.myvocabulary2.activities.UserList
import kotlinx.android.synthetic.main.video_row.view.*

class MainAdapter(val list: List<User>): RecyclerView.Adapter<CustomViewHolder>() {
//    val videoTitles = listOf("First title", "Second", "3rd", "MOOOORE Title")

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellForRow = layoutInflater.inflate(R.layout.video_row, parent, false)
        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val person = list[position]
        holder?.view?.textView_name?.text = person.name
    }
}

class CustomViewHolder(val view: View): RecyclerView.ViewHolder(view){

}