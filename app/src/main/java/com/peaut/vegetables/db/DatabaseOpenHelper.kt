package com.peaut.vegetables.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.peaut.vegetables.util.applicationContext
import org.jetbrains.anko.db.*

/**
 * @author peaut
 * @package  com.peaut.vegetables.db
 * @fileName DatabaseOpenHelper
 * @date on  2019/3/6  17:02
 */
class DatabaseOpenHelper private constructor(context: Context = applicationContext)
    :ManagedSQLiteOpenHelper(context,DB_NAME,null,DB_VERSION){

    companion object {
        val DB_NAME = "vegetables.db"
        val DB_VERSION = 1
        val instance: DatabaseOpenHelper by lazy { DatabaseOpenHelper() }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(SearchHistoryTable.TABLE_NAME,true,
                SearchHistoryTable.KEY to TEXT + PRIMARY_KEY + UNIQUE)
        db?.createTable(AddressTable.TABLE_NAME,true,
                AddressTable.ID to INTEGER + PRIMARY_KEY + UNIQUE,
                AddressTable.USERNAME to TEXT,
                AddressTable.PHONE to TEXT,
                AddressTable.ADDRESS_INFO to TEXT,
                AddressTable.IS_DEFAULT to INTEGER)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable(SearchHistoryTable.TABLE_NAME,true)
        db?.dropTable(AddressTable.TABLE_NAME,true)
        onCreate(db)
    }
}