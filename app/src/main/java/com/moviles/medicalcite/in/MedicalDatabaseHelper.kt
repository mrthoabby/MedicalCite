package com.moviles.medicalcite.`in`

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MedicalDatabaseHelper(
    context: Context?,
    name: String? = DATABASE_NAME,
    factory: SQLiteDatabase.CursorFactory? = null,
    version: Int = DATABASE_VERSION
) : SQLiteOpenHelper(context,  DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(p0: SQLiteDatabase?) {
        MedicalDatabaseContract.CreateEntries(p0)
        var dataProvider = MedicalDatabaseProviderDataInit(p0)
        dataProvider.GenerateDataBaseProvider()
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        MedicalDatabaseContract.DropAllTables(p0)
        onCreate(p0)
    }

    companion object{
        const val DATABASE_NAME = "medicalcite.db"
        const val DATABASE_VERSION = 1
    }
}