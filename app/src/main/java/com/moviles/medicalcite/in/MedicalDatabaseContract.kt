package com.moviles.medicalcite.`in`

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns
import android.provider.BaseColumns._ID

object MedicalDatabaseContract {

    object DoctorEntry:BaseColumns {
        const val TABLE_NAME = "doctor"
        const val DOCTOR_ID = "doctor_id"
        const val DOCTOR_FULLNAME = "doctor_fullname"
        const val FK_ESPECIALIZATION_ID = "fk_especialization_id"

        const val SQL_CREATE_ENTRIES:String = "CREATE TABLE $TABLE_NAME (" +
                "$DOCTOR_ID INTEGER PRIMARY KEY," +
                "$DOCTOR_FULLNAME TEXT NOT NULL," +
                "$FK_ESPECIALIZATION_ID INTEGER NOT NULL)"

        const val DROPTTABLE = "DROP TABLE IF EXIST $TABLE_NAME"
    }
    object MedicalCiteEntry:BaseColumns {
        const val TABLE_NAME = "medicalcite"
        const val MEDICALCITE_ID = "medicalcite_id"
        const val DATE_MEDICALCITE = "date_medicalcite"
        const val FK_DOCTOR_ID = "fk_doctor_id"
        const val FK_USER_ID = "fk_user_id"

        const val SQL_CREATE_ENTRIES:String = "CREATE TABLE $TABLE_NAME (" +
                "$MEDICALCITE_ID INTEGER PRIMARY KEY," +
                "$DATE_MEDICALCITE TEXT NOT NULL," +
                "$FK_DOCTOR_ID INTEGER NOT NULL," +
                "$FK_USER_ID INTEGER)"
        const val DROPTTABLE = "DROP TABLE IF EXIST ${DoctorEntry.TABLE_NAME}"
    }

    object SpecializationEntry:BaseColumns {
        const val TABLE_NAME = "specialization"
        const val SPECIALIZATION_ID = "specialization_id"
        const val SPECIALIZATION_FULLNAME = "specialization_fullname"

        const val SQL_CREATE_ENTRIES:String = "CREATE TABLE $TABLE_NAME (" +
                "$_ID INTEGER PRIMARY KEY," +
                "$SPECIALIZATION_ID INTEGER UNIQUE, "+
                "$SPECIALIZATION_FULLNAME TEXT NOT NULL)"

        const val DROPTTABLE = "DROP TABLE IF EXIST ${DoctorEntry.TABLE_NAME}"
    }

    object UserEntry:BaseColumns {
        const val TABLE_NAME = "user"
        const val USER_ID = "user_id"
        const val USER_FULLNAME = "user_fullname"

        const val SQL_CREATE_ENTRIES:String = "CREATE TABLE $TABLE_NAME (" +
                "$USER_ID INTEGER PRIMARY KEY," +
                "$USER_FULLNAME TEXT NOT NULL)"
        const val DROPTTABLE = "DROP TABLE IF EXIST ${DoctorEntry.TABLE_NAME}"
    }

    fun CreateEntries(db : SQLiteDatabase?){
        db?.execSQL(DoctorEntry.SQL_CREATE_ENTRIES)
        db?.execSQL(MedicalCiteEntry.SQL_CREATE_ENTRIES)
        db?.execSQL(SpecializationEntry.SQL_CREATE_ENTRIES)
        db?.execSQL(UserEntry.SQL_CREATE_ENTRIES)
    }
    fun DropAllTables(db:SQLiteDatabase?){
        db?.execSQL(DoctorEntry.DROPTTABLE)
        db?.execSQL(MedicalCiteEntry.DROPTTABLE)
        db?.execSQL(SpecializationEntry.DROPTTABLE)
        db?.execSQL(UserEntry.DROPTTABLE)
    }



}