package com.example.myvocabulary2.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.myvocabulary2.R
import com.example.myvocabulary2.adapters.MyWordsAdapter
import com.example.myvocabulary2.handlers.DatabaseHandler
import com.example.myvocabulary2.handlers.DatabaseHandlerWords
import com.example.myvocabulary2.model.EmpModelClass
import com.example.myvocabulary2.model.WordClass
import kotlinx.android.synthetic.main.activity_play.*

class PlayActivity : AppCompatActivity() {
    var usedWordsList: Array<String> = arrayOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play)
        viewRecord()
    }

    //method for read records from database in ListView
    private fun viewRecord(usedWords: Array<String> = arrayOf<String>()) {
        //creating the instance of DatabaseHandler class
        val databaseHandler: DatabaseHandler = DatabaseHandler(this)
        val databaseHandlerWords: DatabaseHandlerWords = DatabaseHandlerWords(this)
        //calling the viewEmployee method of DatabaseHandler class to read the records
        val emp: List<EmpModelClass> = databaseHandler.viewEmployee()
        val words: List<WordClass> = databaseHandlerWords.viewWord()
        val empArrayId = Array<String>(emp.size){"0"}
        val empArrayName = Array<String>(emp.size){"null"}
        var index = 0

        for(e in emp){
            empArrayId[index] = (index + 1).toString()
            empArrayName[index] = e.userName
            index++
        }
        val randomWords = words.asSequence().shuffled().take(index)
        val word = Array<String>(emp.size){"0"}
        var ind = 0
        for(e in randomWords) {
            word[ind] = e.word
            ind++
        }
        usedWordsList += word
        //creating custom ArrayAdapter
        val myListAdapter = MyWordsAdapter(this,empArrayId,empArrayName,word)
        playListView.adapter = myListAdapter
    }

    fun nextRound(view: View) {
        // yangi sozlar
        finish()
    }
}