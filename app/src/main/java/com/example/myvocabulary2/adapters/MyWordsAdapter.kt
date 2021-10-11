package com.example.myvocabulary2.adapters
import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.myvocabulary2.R

class MyWordsAdapter(private val context: Activity, private val id: Array<String>, private val name: Array<String>, private val word: Array<String>)
    : ArrayAdapter<String>(context, R.layout.play_list, name) {

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.play_list, null, true)

        val idText = rowView.findViewById(R.id.textView2) as TextView
        val nameText = rowView.findViewById(R.id.textViewName) as TextView
        val wordText = rowView.findViewById(R.id.textViewWord) as TextView

        idText.text = "${id[position]}"
        nameText.text = "Name: ${name[position]}"
        wordText.text = "Word: ${word[position]}"

        return rowView
    }
}