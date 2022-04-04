package com.moviles.medicalcite.`in`

import android.content.ContentValues
import com.moviles.medicalcite.CiteMedical
import com.moviles.medicalcite.Specialization
import com.moviles.medicalcite.UserClient
import com.moviles.medicalcite.`in`.MedicalDatabaseContract.DoctorEntry
import com.moviles.medicalcite.`in`.MedicalDatabaseContract.MedicalCiteEntry
import com.moviles.medicalcite.`in`.MedicalDatabaseContract.SpecializationEntry
import com.moviles.medicalcite.`in`.MedicalDatabaseContract.UserEntry


object DataManager {


    fun GetUserIfExist(userId: String?, dbHelper: MedicalDatabaseHelper): Array<String?> {
        if (userId.toString().toIntOrNull() == null) return arrayOfNulls<String>(0)
        val db = dbHelper.readableDatabase
        val userCursor = db.rawQuery(
            "SELECT * FROM ${UserEntry.TABLE_NAME} WHERE ${UserEntry.USER_ID} = $userId LIMIT ${1};",
            null
        )
        val idColumnName = userCursor.getColumnIndex(UserEntry.USER_FULLNAME)
        val idColumnIdentification = userCursor.getColumnIndex(UserEntry.USER_ID)
        val userIdResult =
            if (userCursor.moveToFirst()) userCursor.getInt(idColumnIdentification) else null

        if (userIdResult != null && userIdResult.toString() == userId) {
            return arrayOf(
                userCursor.getInt(idColumnIdentification).toString(),
                userCursor.getString(idColumnName).toString()
            )
        }
        userCursor.close()
        return arrayOfNulls<String>(0)

    }

    fun GetDoctorIfExist(doctorId: String?, dbHelper: MedicalDatabaseHelper): Array<String?> {
        if (doctorId.toString().toIntOrNull() == null) return arrayOfNulls<String>(0)

        val db = dbHelper.readableDatabase
        val doctorCursor = db.rawQuery(
            "SELECT * FROM ${DoctorEntry.TABLE_NAME} WHERE ${DoctorEntry.DOCTOR_ID} = $doctorId LIMIT ${1};",
            null
        )
        val idColumnName = doctorCursor.getColumnIndex(DoctorEntry.DOCTOR_FULLNAME)
        val idColumnIdentification = doctorCursor.getColumnIndex(DoctorEntry.DOCTOR_ID)
        val doctorIdResult =
            if (doctorCursor.moveToFirst()) doctorCursor.getInt(idColumnIdentification) else null

        if (doctorIdResult != null && doctorIdResult.toString().equals(doctorId, true)) {
            return arrayOf(
                doctorCursor.getInt(idColumnIdentification).toString(),
                doctorCursor.getString(idColumnName).toString()
            )
        }
        doctorCursor.close()
        return arrayOfNulls<String>(0)

    }

    fun getSpecializations(dbHelper: MedicalDatabaseHelper): Array<Specialization> {
        val db = dbHelper.readableDatabase
        var resultArray = arrayOf<Specialization>()
        val specializationColums = arrayOf(
            SpecializationEntry.SPECIALIZATION_ID,
            SpecializationEntry.SPECIALIZATION_FULLNAME
        )
        val specializationCursor =
            db.query(
                SpecializationEntry.TABLE_NAME,
                specializationColums,
                null,
                null,
                null,
                null,
                null
            )
        val idColumnName =
            specializationCursor.getColumnIndex(SpecializationEntry.SPECIALIZATION_FULLNAME)
        val idColumnIdentification =
            specializationCursor.getColumnIndex(SpecializationEntry.SPECIALIZATION_ID)
        while (specializationCursor.moveToNext()) {
            resultArray += Specialization(
                specializationCursor.getInt(idColumnIdentification),
                specializationCursor.getString(idColumnName)
            )
        }
        specializationCursor.close()
        return resultArray
    }

    fun getMedicalCiteAviables(
        specializationId: Int,
        dbHelper: MedicalDatabaseHelper
    ): Array<CiteMedical> {
        val db = dbHelper.readableDatabase
        var resultArray = arrayOf<CiteMedical>()
        val returnCiteCursor = db.rawQuery(
            "SELECT " +
                    "${DoctorEntry.DOCTOR_FULLNAME}," +
                    "${SpecializationEntry.SPECIALIZATION_FULLNAME}," +
                    "${SpecializationEntry.SPECIALIZATION_ID}," +
                    "${MedicalCiteEntry.DATE_MEDICALCITE}," +
                    "${MedicalCiteEntry.MEDICALCITE_ID}  " +
                    "FROM ${DoctorEntry.TABLE_NAME} " +
                    "JOIN ${SpecializationEntry.TABLE_NAME} " +
                    "ON ${DoctorEntry.TABLE_NAME}.${DoctorEntry.FK_ESPECIALIZATION_ID}=${SpecializationEntry.TABLE_NAME}.${SpecializationEntry.SPECIALIZATION_ID} " +
                    "JOIN ${MedicalCiteEntry.TABLE_NAME} " +
                    "ON ${DoctorEntry.TABLE_NAME}.${DoctorEntry.DOCTOR_ID}=${MedicalCiteEntry.TABLE_NAME}.${MedicalCiteEntry.FK_DOCTOR_ID} " +
                    "WHERE ${SpecializationEntry.TABLE_NAME}.${SpecializationEntry.SPECIALIZATION_ID} = $specializationId " +
                    "AND ${MedicalCiteEntry.TABLE_NAME}.${MedicalCiteEntry.FK_USER_ID} IS NULL;",
            null
        )

        val idColumnDoctorName = returnCiteCursor.getColumnIndex(DoctorEntry.DOCTOR_FULLNAME)
        val idColumnSpecializationName =
            returnCiteCursor.getColumnIndex(SpecializationEntry.SPECIALIZATION_FULLNAME)
        val idColumnSpecializationId =
            returnCiteCursor.getColumnIndex(SpecializationEntry.SPECIALIZATION_ID)
        val idColumnDateMedicalCite =
            returnCiteCursor.getColumnIndex(MedicalCiteEntry.DATE_MEDICALCITE)
        val idColumnIdMedicalCite =
            returnCiteCursor.getColumnIndex(MedicalCiteEntry.MEDICALCITE_ID)

        while (returnCiteCursor.moveToNext()) {
            resultArray += CiteMedical(
                returnCiteCursor.getString(idColumnDoctorName),
                returnCiteCursor.getString(idColumnSpecializationName),
                returnCiteCursor.getInt(idColumnSpecializationId),
                returnCiteCursor.getString(idColumnDateMedicalCite),
                returnCiteCursor.getInt(idColumnIdMedicalCite)
            )
        }
        returnCiteCursor.close()
        return resultArray

    }
    fun getMedicalCite(
        dataCiteId: Int,
        dbHelper: MedicalDatabaseHelper
    ): CiteMedical? {
        val db = dbHelper.readableDatabase
        val returnCiteCursor = db.rawQuery(
            "SELECT " +
                    "${DoctorEntry.DOCTOR_FULLNAME}," +
                    "${SpecializationEntry.SPECIALIZATION_FULLNAME}," +
                    "${SpecializationEntry.SPECIALIZATION_ID}," +
                    "${MedicalCiteEntry.DATE_MEDICALCITE}," +
                    "${MedicalCiteEntry.MEDICALCITE_ID}  " +
                    "FROM ${DoctorEntry.TABLE_NAME} " +
                    "JOIN ${SpecializationEntry.TABLE_NAME} " +
                    "ON ${DoctorEntry.TABLE_NAME}.${DoctorEntry.FK_ESPECIALIZATION_ID}=${SpecializationEntry.TABLE_NAME}.${SpecializationEntry.SPECIALIZATION_ID} " +
                    "JOIN ${MedicalCiteEntry.TABLE_NAME} " +
                    "ON ${DoctorEntry.TABLE_NAME}.${DoctorEntry.DOCTOR_ID}=${MedicalCiteEntry.TABLE_NAME}.${MedicalCiteEntry.FK_DOCTOR_ID} " +
                    "WHERE ${MedicalCiteEntry.TABLE_NAME}.${MedicalCiteEntry.MEDICALCITE_ID} = $dataCiteId;",
            null
        )

        val idColumnDoctorName = returnCiteCursor.getColumnIndex(DoctorEntry.DOCTOR_FULLNAME)
        val idColumnSpecializationName =
            returnCiteCursor.getColumnIndex(SpecializationEntry.SPECIALIZATION_FULLNAME)
        val idColumnSpecializationId =
            returnCiteCursor.getColumnIndex(SpecializationEntry.SPECIALIZATION_ID)
        val idColumnDateMedicalCite =
            returnCiteCursor.getColumnIndex(MedicalCiteEntry.DATE_MEDICALCITE)
        val idColumnIdMedicalCite =
            returnCiteCursor.getColumnIndex(MedicalCiteEntry.MEDICALCITE_ID)

        return if (returnCiteCursor.moveToNext())  CiteMedical(
                returnCiteCursor.getString(idColumnDoctorName),
                returnCiteCursor.getString(idColumnSpecializationName),
                returnCiteCursor.getInt(idColumnSpecializationId),
                returnCiteCursor.getString(idColumnDateMedicalCite),
                returnCiteCursor.getInt(idColumnIdMedicalCite)
            ) else null

    }
    fun getClientsFromDoctor(doctorId: Int, dbHelper: MedicalDatabaseHelper): Array<UserClient> {
        val db = dbHelper.readableDatabase
        var resultArray = arrayOf<UserClient>()
        val returnCiteCursor = db.rawQuery(
            "SELECT " +
                    "${UserEntry.USER_FULLNAME}," +
                    "${SpecializationEntry.SPECIALIZATION_FULLNAME}," +
                    "${MedicalCiteEntry.DATE_MEDICALCITE} " +
                    "FROM ${DoctorEntry.TABLE_NAME} JOIN ${SpecializationEntry.TABLE_NAME} " +
                    "ON ${DoctorEntry.TABLE_NAME}.${DoctorEntry.FK_ESPECIALIZATION_ID}=${SpecializationEntry.TABLE_NAME}.${SpecializationEntry.SPECIALIZATION_ID} " +
                    "JOIN ${MedicalCiteEntry.TABLE_NAME} " +
                    "ON ${DoctorEntry.TABLE_NAME}.${DoctorEntry.DOCTOR_ID}=${MedicalCiteEntry.TABLE_NAME}.${MedicalCiteEntry.FK_DOCTOR_ID} " +
                    "JOIN ${UserEntry.TABLE_NAME} " +
                    "ON ${UserEntry.TABLE_NAME}.${UserEntry.USER_ID}=${MedicalCiteEntry.TABLE_NAME}.${MedicalCiteEntry.FK_USER_ID} " +
                    "WHERE ${MedicalCiteEntry.TABLE_NAME}.${MedicalCiteEntry.FK_DOCTOR_ID}= $doctorId " +
                    "AND ${MedicalCiteEntry.TABLE_NAME}.${MedicalCiteEntry.FK_USER_ID} IS NOT NULL;",
            null
        )

        val idColumnUserName = returnCiteCursor.getColumnIndex(UserEntry.USER_FULLNAME)
        val idColumnSpecializationName =
            returnCiteCursor.getColumnIndex(SpecializationEntry.SPECIALIZATION_FULLNAME)
        val idColumnDateMedicalCite =
            returnCiteCursor.getColumnIndex(MedicalCiteEntry.DATE_MEDICALCITE)

        while (returnCiteCursor.moveToNext()) {
            resultArray += UserClient(
                returnCiteCursor.getString(idColumnUserName),
                returnCiteCursor.getString(idColumnDateMedicalCite),
                returnCiteCursor.getString(idColumnSpecializationName)
            )
        }
        returnCiteCursor.close()
        return resultArray
    }

    fun toAsignCite(userId:Int,citeId:Int?,dbHelper: MedicalDatabaseHelper) :Boolean{
        val values = ContentValues()
        values.put(MedicalCiteEntry.FK_USER_ID,userId)
        val db = dbHelper.readableDatabase
        val result = db.update(MedicalCiteEntry.TABLE_NAME,values,"${MedicalCiteEntry.MEDICALCITE_ID}=$citeId",null)
        return result > 0
    }



}