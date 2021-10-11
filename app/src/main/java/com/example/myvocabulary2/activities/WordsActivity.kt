package com.example.myvocabulary2.activities

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.myvocabulary2.R
import com.example.myvocabulary2.adapters.MyListAdapter
import com.example.myvocabulary2.handlers.DatabaseHandler
import com.example.myvocabulary2.handlers.DatabaseHandlerWords
import com.example.myvocabulary2.model.EmpModelClass
import com.example.myvocabulary2.model.WordClass
import kotlinx.android.synthetic.main.activity_main.*

class WordsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_words)
    }

    //method for saving records in database
    fun saveRecord(view: View){
        val id = u_id.text.toString()
        val name = u_name.text.toString()
        val databaseHandler: DatabaseHandlerWords = DatabaseHandlerWords(this)
        if(id.trim()!="" && name.trim()!=""){
            val status = databaseHandler.addWord(WordClass(Integer.parseInt(id),name))
            if(status > -1){
                Toast.makeText(applicationContext,"record save", Toast.LENGTH_LONG).show()
                u_id.text.clear()
                u_name.text.clear()
            }
        }else{
            Toast.makeText(applicationContext,"id or name or email cannot be blank", Toast.LENGTH_LONG).show()
        }

    }
    //method for read records from database in ListView
    fun viewRecord(view: View){
        //creating the instance of DatabaseHandler class
        val databaseHandler: DatabaseHandlerWords = DatabaseHandlerWords(this)
        //calling the viewEmployee method of DatabaseHandler class to read the records
        val emp: List<WordClass> = databaseHandler.viewWord()
        val empArrayId = Array<String>(emp.size){"0"}
        val empArrayName = Array<String>(emp.size){"null"}
        var index = 0
        for(e in emp){
            empArrayId[index] = e.wordId.toString()
            empArrayName[index] = e.word
            index++
        }
        //creating custom ArrayAdapter
        val myListAdapter = MyListAdapter(this,empArrayId,empArrayName)
        listView.adapter = myListAdapter
    }
    //method for updating records based on user id
    fun updateRecord(view: View){
        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.update_dialog, null)
        dialogBuilder.setView(dialogView)

        val edtId = dialogView.findViewById(R.id.updateId) as EditText
        val edtName = dialogView.findViewById(R.id.updateName) as EditText

        dialogBuilder.setTitle("Update Record")
        dialogBuilder.setMessage("Enter data below")
        dialogBuilder.setPositiveButton("Update", DialogInterface.OnClickListener { _, _ ->

            val updateId = edtId.text.toString()
            val updateName = edtName.text.toString()
            //creating the instance of DatabaseHandler class
            val databaseHandler: DatabaseHandlerWords = DatabaseHandlerWords(this)
            if(updateId.trim()!="" && updateName.trim()!=""){
                //calling the updateEmployee method of DatabaseHandler class to update record
                val status = databaseHandler.updateWord(WordClass(Integer.parseInt(updateId),updateName))
                if(status > -1){
                    Toast.makeText(applicationContext,"record update", Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(applicationContext,"id or name or email cannot be blank", Toast.LENGTH_LONG).show()
            }

        })
        dialogBuilder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which ->
            //pass
        })
        val b = dialogBuilder.create()
        b.show()
    }
    //method for deleting records based on id
    fun deleteRecord(view: View){
        //creating AlertDialog for taking user id
        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.delete_dialog, null)
        dialogBuilder.setView(dialogView)

        val dltId = dialogView.findViewById(R.id.deleteId) as EditText
        dialogBuilder.setTitle("Delete Record")
        dialogBuilder.setMessage("Enter id below")
        dialogBuilder.setPositiveButton("Delete", DialogInterface.OnClickListener { _, _ ->

            val deleteId = dltId.text.toString()
            //creating the instance of DatabaseHandler class
            val databaseHandler: DatabaseHandlerWords = DatabaseHandlerWords(this)
            if(deleteId.trim()!=""){
                //calling the deleteEmployee method of DatabaseHandler class to delete record
                val status = databaseHandler.deleteWord(WordClass(Integer.parseInt(deleteId),""))
                if(status > -1){
                    Toast.makeText(applicationContext,"record deleted", Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(applicationContext,"id or name or email cannot be blank", Toast.LENGTH_LONG).show()
            }

        })
        dialogBuilder.setNegativeButton("Cancel", DialogInterface.OnClickListener { _, _ ->
            //pass
        })
        val b = dialogBuilder.create()
        b.show()
    }
}