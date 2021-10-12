package com.example.myvocabulary2.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.myvocabulary2.MainActivity
import com.example.myvocabulary2.R

class MenuActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
    }

    fun play(view: View) {
        startActivity(Intent(this, PlayActivity::class.java))
    }

    fun players(view: View) {
        startActivity(Intent(this, MainActivity::class.java))
    }

    fun words(view: View) {
        startActivity(Intent(this, WordsActivity::class.java))
    }
}