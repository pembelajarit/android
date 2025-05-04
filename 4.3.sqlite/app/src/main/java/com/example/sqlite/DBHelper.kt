package com.example.sqlite

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    // Called when the database is created for the first time
    override fun onCreate(db: SQLiteDatabase) {
        val createTableQuery = """
            CREATE TABLE $TABLE_NAME (
                $ID_COL INTEGER PRIMARY KEY AUTOINCREMENT,
                $NAME_COL TEXT,
                $AGE_COL TEXT
            )
        """.trimIndent()

        db.execSQL(createTableQuery)
    }

    // Called when the database needs to be upgraded
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    // Inserts a new record into the database
    fun addName(name: String, age: String) {
        val values = ContentValues().apply {
            put(NAME_COL, name)
            put(AGE_COL, age)
        }

        writableDatabase.use { db ->
            db.insert(TABLE_NAME, null, values)
        }
    }

    // Retrieves all records from the database
    fun getName(): Cursor {
        return readableDatabase.rawQuery("SELECT * FROM $TABLE_NAME", null)
    }

    companion object {
        private const val DATABASE_NAME = "DB_ARSIP"
        private const val DATABASE_VERSION = 1
        const val TABLE_NAME = "tb_person"
        const val ID_COL = "id"
        const val NAME_COL = "name"
        const val AGE_COL = "age"
    }
}