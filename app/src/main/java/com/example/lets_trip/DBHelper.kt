package com.example.lets_trip

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_NAME = "UserDB"
        const val DATABASE_VERSION = 1
        const val TABLE_NAME = "users"
        const val COLUMN_USERNAME = "username"
        const val COLUMN_PASSWORD = "password"

        const val TABLE_PLAN = "planDB"
        const val COLUMN_LOCATION = "location"
        const val COLUMN_DEPARTURE = "departure"
        const val COLUMN_ARRIVAL = "arrival"
        const val COLUMN_FRIEND_NUMBER = "f_num"
        const val COLUMN_FRIEND_NAME = "f_name"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = "CREATE TABLE $TABLE_NAME ($COLUMN_USERNAME TEXT PRIMARY KEY, $COLUMN_PASSWORD TEXT)"
        db?.execSQL(createTableQuery)

        val createPlanTableQuery =
            "CREATE TABLE $TABLE_PLAN ($COLUMN_LOCATION TEXT PRIMARY KEY, $COLUMN_DEPARTURE TEXT, $COLUMN_ARRIVAL TEXT, $COLUMN_FRIEND_NUMBER TEXT, $COLUMN_FRIEND_NAME TEXT)"
        db?.execSQL(createPlanTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val dropTableQuery = "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(dropTableQuery)

        val dropPlanTableQuery = "DROP TABLE IF EXISTS $TABLE_PLAN"
        db?.execSQL(dropPlanTableQuery)

        onCreate(db)
    }

    fun insertUser(username: String, password: String): Long {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_USERNAME, username)
            put(COLUMN_PASSWORD, password)
        }
        return db.insert(TABLE_NAME, null, values)
    }

    fun insertPlan(location: String, departure: String, arrival: String, f_num: String, f_name: String): Long {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_LOCATION, location)
            put(COLUMN_DEPARTURE, departure)
            put(COLUMN_ARRIVAL, arrival)
            put(COLUMN_FRIEND_NUMBER, f_num)
            put(COLUMN_FRIEND_NAME, f_name)
        }
        return db.insert(TABLE_PLAN, null, values)
    }

}