package com.moviles.medicalcite.`in`

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase

class MedicalDatabaseProviderDataInit(val dbControler:SQLiteDatabase?) {
     private enum class SPECIALIZATIONS(val ID:Int,val NAME:String){
        MEDICINE_GENERAL(901,"Medicina General"),
        PEDIATRIA(902,"Pediatría"),
        ODONTOLOGIA(903,"Odontología"),
        CITOGLOGIA(904,"Citología"),
        MUESTRA_LABORATORIO(905,"Toma de muestra de laboratorio"),
        COVID19(906,"Vacunación COVID19"),
        OPTOMETRIA(907,"Optometría"),
        AUTORIZACIONES(908,"Autorizaciones")
    }
    private fun insertDoctors(){
        insertDoctor(1,"Tracy Benitez", SPECIALIZATIONS.MEDICINE_GENERAL.ID)
        insertDoctor(2,"Dale Peterson", SPECIALIZATIONS.MEDICINE_GENERAL.ID)
        insertDoctor(3,"Brenda Williams", SPECIALIZATIONS.MEDICINE_GENERAL.ID)

        insertDoctor(4,"Noa Frías Moya", SPECIALIZATIONS.ODONTOLOGIA.ID)
        insertDoctor(5,"Ezequiel Armas Iborra", SPECIALIZATIONS.ODONTOLOGIA.ID,)
        insertDoctor(6,"Anthony Potter", SPECIALIZATIONS.ODONTOLOGIA.ID)

        insertDoctor(7,"Olimpia Herrero Nogués", SPECIALIZATIONS.CITOGLOGIA.ID)
        insertDoctor(9,"Jessica Rodriguez", SPECIALIZATIONS.CITOGLOGIA.ID)
        insertDoctor(10,"Patrick Fitzgerald", SPECIALIZATIONS.CITOGLOGIA.ID)

        insertDoctor(11,"María Del Carmen Miralles Elías", SPECIALIZATIONS.PEDIATRIA.ID)
        insertDoctor(12,"Eva Jover", SPECIALIZATIONS.PEDIATRIA.ID)
        insertDoctor(13,"April Gordon", SPECIALIZATIONS.PEDIATRIA.ID)

        insertDoctor(14,"Luís Fernandez Costa", SPECIALIZATIONS.MUESTRA_LABORATORIO.ID)
        insertDoctor(15,"Diego Espiridión Páez Carrasco", SPECIALIZATIONS.MUESTRA_LABORATORIO.ID)
        insertDoctor(16,"Severo Palomo-Esteban", SPECIALIZATIONS.MUESTRA_LABORATORIO.ID)

        insertDoctor(17,"Joaquina Galvez Niño", SPECIALIZATIONS.COVID19.ID)
        insertDoctor(18,"Alma Navarrete", SPECIALIZATIONS.COVID19.ID)
        insertDoctor(19,"Hilda Gibert-Echeverría", SPECIALIZATIONS.COVID19.ID)

        insertDoctor(20,"Aarón Cerro Páez", SPECIALIZATIONS.OPTOMETRIA.ID)
        insertDoctor(21,"Virgilio Poza Aragón", SPECIALIZATIONS.OPTOMETRIA.ID)
        insertDoctor(22,"Gustavo Vizcaíno Catalán", SPECIALIZATIONS.OPTOMETRIA.ID)

        insertDoctor(23,"Salomé Caballero Estrada", SPECIALIZATIONS.AUTORIZACIONES.ID)
        insertDoctor(24,"Josefina Barranco Roman", SPECIALIZATIONS.AUTORIZACIONES.ID)
        insertDoctor(25,"Alexandra Barral Zabaleta", SPECIALIZATIONS.AUTORIZACIONES.ID)
    }
    private fun insertMedicalCites(){
        insertMedicalCite("21-04-2022 H 06:30",1)
        insertMedicalCite("26-04-2022 H 12:15",1)
        insertMedicalCite("30-04-2022 H 14:15",1)

        insertMedicalCite("21-04-2022 H 05:30",2)
        insertMedicalCite("26-04-2022 H 09:15",2)
        insertMedicalCite("30-04-2022 H 16:15",2)

        insertMedicalCite("21-04-2022 H 06:30",3)
        insertMedicalCite("26-04-2022 H 12:15",3)
        insertMedicalCite("30-04-2022 H 14:15",3)

        insertMedicalCite("21-04-2022 H 07:30",4)
        insertMedicalCite("26-04-2022 H 13:05",4)
        insertMedicalCite("30-04-2022 H 10:15",4)

        insertMedicalCite("21-04-2022 H 08:30",5)
        insertMedicalCite("26-04-2022 H 12:45",5)
        insertMedicalCite("30-04-2022 H 16:05",5)

        insertMedicalCite("21-04-2022 H 10:30",6)
        insertMedicalCite("26-04-2022 H 05:15",6)
        insertMedicalCite("30-04-2022 H 17:15",6)

        insertMedicalCite("21-04-2022 H 06:00",7)
        insertMedicalCite("26-04-2022 H 06:00",7)
        insertMedicalCite("30-04-2022 H 06:00",7)

        insertMedicalCite("21-04-2022 H 06:30",8)
        insertMedicalCite("26-04-2022 H 12:15",8)
        insertMedicalCite("30-04-2022 H 14:15",8)

        insertMedicalCite("21-04-2022 H 07:00",9)
        insertMedicalCite("26-04-2022 H 08:00",9)
        insertMedicalCite("30-04-2022 H 10:15",9)

        insertMedicalCite("21-04-2022 H 06:00",10)
        insertMedicalCite("26-04-2022 H 11:15",10)
        insertMedicalCite("30-04-2022 H 05:15",10)

        insertMedicalCite("12-04-2022 H 12:00",11)
        insertMedicalCite("12-05-2022 H 18:00",11)
        insertMedicalCite("26-04-2022 H 19:15",11)

        insertMedicalCite("21-04-2022 H 20:15",12)
        insertMedicalCite("18-04-2022 H 16:30",12)
        insertMedicalCite("08-06-2022 H 06:30",12)

        insertMedicalCite("08-05-2022 H 18:30",13)
        insertMedicalCite("28-04-2022 H 05:00",13)
        insertMedicalCite("01-05-2022 H 06:00",13)

        insertMedicalCite("16-04-2022 H 08:15",14)
        insertMedicalCite("21-04-2022 H 08:30",14)
        insertMedicalCite("22-04-2022 H 09:05",14)
        insertMedicalCite("15-05-2022 H 06:40",14)
        insertMedicalCite("15-05-2022 H 10:00",14)
        insertMedicalCite("08-06-2022 H 11:30",14)

        insertMedicalCite("08-07-2022 H 06:30",15)
        insertMedicalCite("16-04-2022 H 08:30",15)
        insertMedicalCite("17-04-2022 H 09:30",15)

        insertMedicalCite("18-04-2022 H 10:00",16)
        insertMedicalCite("19-04-2022 H 06:00",16)
        insertMedicalCite("20-04-2022 H 08:30",16)

        insertMedicalCite("16-04-2022 H 16:00",17)
        insertMedicalCite("17-04-2022 H 06:00",17)
        insertMedicalCite("18-04-2022 H 18:00",17)

        insertMedicalCite("18-04-2022 H 20:00",18)
        insertMedicalCite("18-04-2022 H 20:30",18)
        insertMedicalCite("20-04-2022 H 21:00",18)

        insertMedicalCite("20-04-2022 H 19:00",19)
        insertMedicalCite("20-04-2022 H 16:30",19)
        insertMedicalCite("21-04-2022 H 18:00",19)

        insertMedicalCite("20-04-2022 H 16:50",20)
        insertMedicalCite("28-04-2022 H 16:30",20)
        insertMedicalCite("21-04-2022 H 06:30",20)

        insertMedicalCite("30-04-2022 H 07:30",21)
        insertMedicalCite("01-05-2022 H 16:00",21)
        insertMedicalCite("02-05-2022 H 20:30",21)

        insertMedicalCite("03-05-2022 H 08:00",22)
        insertMedicalCite("03-05-2022 H 17:30",22)
        insertMedicalCite("03-05-2022 H 06:00",22)

        insertMedicalCite("03-05-2022 H 09:00",23)
        insertMedicalCite("04-05-2022 H 09:30",23)
        insertMedicalCite("04-05-2022 H 10:30",23)

        insertMedicalCite("04-05-2022 H 10:30",24)
        insertMedicalCite("04-05-2022 H 11:00",24)
        insertMedicalCite("04-05-2022 H 13:00",24)

        insertMedicalCite("21-04-2022 H 06:00",25)
        insertMedicalCite("08-05-2022 H 06:00",25)
        insertMedicalCite("08-05-2022 H 09:30",25)

    }
    private fun insertSpecializations(){
        insertSpecialization(SPECIALIZATIONS.MEDICINE_GENERAL.ID, SPECIALIZATIONS.MEDICINE_GENERAL.NAME)
        insertSpecialization(SPECIALIZATIONS.PEDIATRIA.ID, SPECIALIZATIONS.PEDIATRIA.NAME)
        insertSpecialization(SPECIALIZATIONS.ODONTOLOGIA.ID, SPECIALIZATIONS.ODONTOLOGIA.NAME)
        insertSpecialization(SPECIALIZATIONS.CITOGLOGIA.ID, SPECIALIZATIONS.CITOGLOGIA.NAME)
        insertSpecialization(
            SPECIALIZATIONS.MUESTRA_LABORATORIO.ID,
            SPECIALIZATIONS.MUESTRA_LABORATORIO.NAME)
        insertSpecialization(SPECIALIZATIONS.COVID19.ID, SPECIALIZATIONS.COVID19.NAME)
        insertSpecialization(SPECIALIZATIONS.OPTOMETRIA.ID, SPECIALIZATIONS.OPTOMETRIA.NAME)
        insertSpecialization(SPECIALIZATIONS.AUTORIZACIONES.ID, SPECIALIZATIONS.AUTORIZACIONES.NAME)
    }
    private  fun insertUsers(){
        insertUser(1234,"Elena Valentina Mulet Gibert")
        insertUser(4321,"Nacho Cuenca-Lopez")
        insertUser(1111,"Moreno Pacheco-Montes")
        insertUser(2222,"Rosendo Lobato Galan")
        insertUser(3333,"Concepción Mateo")
    }

    private fun insertDoctor(doctorId:Int, doctorFullName:String,foraingKeyspecializationId:Int){
        var values = ContentValues()
        values.put(MedicalDatabaseContract.DoctorEntry.DOCTOR_ID,doctorId)
        values.put(MedicalDatabaseContract.DoctorEntry.DOCTOR_FULLNAME,doctorFullName)
        values.put(MedicalDatabaseContract.DoctorEntry.FK_ESPECIALIZATION_ID,foraingKeyspecializationId)

        var resultRowId = dbControler?.insert(MedicalDatabaseContract.DoctorEntry.TABLE_NAME,null,values)
    }
    private fun insertMedicalCite(dateMedicalCite:String,foraingKeyDoctorId:Int){
        var values = ContentValues()
        values.put(MedicalDatabaseContract.MedicalCiteEntry.DATE_MEDICALCITE,dateMedicalCite)
        values.put(MedicalDatabaseContract.MedicalCiteEntry.FK_DOCTOR_ID,foraingKeyDoctorId)

        var resultRowId = dbControler?.insert(MedicalDatabaseContract.MedicalCiteEntry.TABLE_NAME,null,values)
    }
    private fun insertSpecialization(specializationId:Int, specializationFullName:String){
        var values = ContentValues()
        values.put(MedicalDatabaseContract.SpecializationEntry.SPECIALIZATION_ID,specializationId)
        values.put(MedicalDatabaseContract.SpecializationEntry.SPECIALIZATION_FULLNAME,specializationFullName)

        var resultRowId = dbControler?.insert(MedicalDatabaseContract.SpecializationEntry.TABLE_NAME,null,values)
    }
    private fun insertUser(userId:Int,userFullName:String){
        var values = ContentValues()
        values.put(MedicalDatabaseContract.UserEntry.USER_ID,userId)
        values.put(MedicalDatabaseContract.UserEntry.USER_FULLNAME,userFullName)

        var resultRowId = dbControler?.insert(MedicalDatabaseContract.UserEntry.TABLE_NAME,null,values)
    }

    fun GenerateDataBaseProvider(){
        insertUsers()
        insertSpecializations()
        insertDoctors()
        insertMedicalCites()
    }
}