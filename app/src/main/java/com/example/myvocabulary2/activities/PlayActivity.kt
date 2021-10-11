package com.example.myvocabulary2.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.myvocabulary2.R
import com.example.myvocabulary2.adapters.MyWordsAdapter
import com.example.myvocabulary2.handlers.DatabaseHandler
import com.example.myvocabulary2.model.EmpModelClass
import kotlinx.android.synthetic.main.activity_play.*

class PlayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play)
        viewRecord()
    }

    //method for read records from database in ListView
    private fun viewRecord() {
        //creating the instance of DatabaseHandler class
        val databaseHandler: DatabaseHandler = DatabaseHandler(this)
        //calling the viewEmployee method of DatabaseHandler class to read the records
        val emp: List<EmpModelClass> = databaseHandler.viewEmployee()
        val empArrayId = Array<String>(emp.size){"0"}
        val empArrayName = Array<String>(emp.size){"null"}
        var index = 0
        val word = Array<String>(emp.size){"0"}
        for(e in emp){
            empArrayId[index] = (index + 1).toString()
            empArrayName[index] = e.userName
            word[index] = e.userName
            index++
        }
        //creating custom ArrayAdapter
        val myListAdapter = MyWordsAdapter(this,empArrayId,empArrayName,word)
        playListView.adapter = myListAdapter
    }

    fun nextRound(view: View) {
        // yangi sozlar
        finish()
    }
}