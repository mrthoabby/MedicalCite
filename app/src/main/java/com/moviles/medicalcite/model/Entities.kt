package com.moviles.medicalcite

data class Specialization(val specializationid: Int, val specializationName: String) {
    override fun toString(): String {
        return specializationName
    }
}

data class CiteMedical(
    val nameDoctor: String,
    val specializationName: String,
    val specializationid: Int,
    val dateAviable: String,
    val dateId:Int
) {
    var positionList: Int
        get() {
            return positionList
        }
        set(value: Int) {
            positionList = value
        }

}

data class UserClient(val nameUser: String, val dateCite: String, val specializationName: String)


