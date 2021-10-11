package com.example.myvocabulary2.handlers

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteException
import com.example.myvocabulary2.model.WordClass

//creating the database logic, extending the SQLiteOpenHelper base class
class DatabaseHandlerWords(context: Context): SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION) {
    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "WordsDatabase"
        private val TABLE_WORDS = "WordsTable"
        private val KEY_ID = "id"
        private val KEY_NAME = "name"
    }
    override fun onCreate(db: SQLiteDatabase?) {
        // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        //creating table with fields
        val CREATE_WORDS_TABLE = ("CREATE TABLE " + TABLE_WORDS + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"  + KEY_NAME + " TEXT" + ")")
        db?.execSQL(CREATE_WORDS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //  TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        db!!.execSQL("DROP TABLE IF EXISTS " + TABLE_WORDS)
        onCreate(db)
    }


    //method to insert data
    fun addWord(emp: WordClass):Long{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(DatabaseHandlerWords.KEY_ID, emp.wordId)
        contentValues.put(DatabaseHandlerWords.KEY_NAME, emp.word) // WordClass Name
        // Inserting Row
        val success = db.insert(DatabaseHandlerWords.TABLE_WORDS, null, contentValues)
        //2nd argument is String containing nullColumnHack
        db.close() // Closing database connection
        return success
    }
    //method to read data
    fun viewWord():List<WordClass>{
        val empList:ArrayList<WordClass> = ArrayList<WordClass>()
        val selectQuery = "SELECT  * FROM ${DatabaseHandlerWords.TABLE_WORDS}"
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try{
            cursor = db.rawQuery(selectQuery, null)
        }catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var userId: Int
        var userName: String
        if (cursor.moveToFirst()) {
            do {
                userId = cursor.getInt(cursor.getColumnIndex("id"))
                userName = cursor.getString(cursor.getColumnIndex("name"))
                val emp= WordClass(wordId = userId, word = userName)
                empList.add(emp)
            } while (cursor.moveToNext())
        }
        return empList
    }
    //method to update data
    fun updateWord(emp: WordClass):Int{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(DatabaseHandlerWords.KEY_ID, emp.wordId)
        contentValues.put(DatabaseHandlerWords.KEY_NAME, emp.word) // EmpModelClass Name

        // Updating Row
        val success = db.update(DatabaseHandlerWords.TABLE_WORDS, contentValues,"id="+emp.wordId,null)
        //2nd argument is String containing nullColumnHack
        db.close() // Closing database connection
        return success
    }
    //method to delete data
    fun deleteWord(emp: WordClass):Int{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(DatabaseHandlerWords.KEY_ID, emp.wordId) // WordClass UserId
        // Deleting Row
        val success = db.delete(DatabaseHandlerWords.TABLE_WORDS,"id="+emp.wordId,null)
        //2nd argument is String containing nullColumnHack
        db.close() // Closing database connection
        return success
    }
}