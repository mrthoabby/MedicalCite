package com.moviles.medicalcite.ui

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.moviles.medicalcite.EXTRA_ID_LOGIN
import com.moviles.medicalcite.EXTRA_NAME_LOGIN
import com.moviles.medicalcite.R
import com.moviles.medicalcite.`in`.DataManager
import com.moviles.medicalcite.`in`.MedicalDatabaseHelper
import kotlinx.android.synthetic.main.activity_main.*

class LoginActivity : AppCompatActivity() {

    //<editor-fold desc="Define Properties">
    private lateinit var medicalDataHelper: MedicalDatabaseHelper
    //</editor-fold>

    //<editor-fold desc="Override Methods">
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Define code settings
        medicalDataHelper = MedicalDatabaseHelper(this)

        //Define View settings
        inicializeDisplayValues()


        //Define View Events
        btnLogin.setOnClickListener {

            val inputCredentialsString = getAvalidInput(inputNumberUserId)
            val inputPasswordString = getAvalidInput(inputPassword)
            if (!inputCredentialsString.isNullOrEmpty() && !inputPasswordString.isNullOrEmpty() && chekPolices.isChecked) {
                val listLoginResults = if (radioIsUser.isChecked) DataManager.GetUserIfExist(inputCredentialsString.split('m')[0], medicalDataHelper) else DataManager.GetDoctorIfExist(inputCredentialsString.split('m')[0], medicalDataHelper)
                startLogin(this,listLoginResults, inputPasswordString, radioIsUser.isChecked)
            } else {
                Toast.makeText(this, R.string.alerfillFields, Toast.LENGTH_SHORT).show()
            }


        }
    }

    override fun onDestroy() {
        medicalDataHelper.close()
        super.onDestroy()
    }
    //</editor-fold>


    //<editor-fold desc="Executions Methods>"
    private fun inicializeDisplayValues() {
        radioIsUser.isChecked = true
        chekPolices.isChecked = true
    }

    private fun startLogin(context:Context, listLoginResult: Array<String?>, password: String, isUser: Boolean) {
        if (!listLoginResult.isNullOrEmpty() && isValidId(inputNumberUserId.text.toString().trim(),listLoginResult[0]) &&  isvalidPassWord(listLoginResult[0], password)) {
            val activityIntent = if  (isUser)  Intent(context, BoardInit::class.java) else Intent(context, BoardInitDoctor::class.java)
            putExtraDataLogin(activityIntent, listLoginResult)
            startActivity(activityIntent)
            finish()
        } else {
            Toast.makeText(this, R.string.alertUserNoRegist, Toast.LENGTH_SHORT).show()
        }
    }

    private fun putExtraDataLogin(
        activityIntent: Intent,
        listLoginResult: Array<String?>
    ) {
        activityIntent.putExtra(EXTRA_ID_LOGIN, listLoginResult[0].toString().toInt())
        activityIntent.putExtra(EXTRA_NAME_LOGIN, listLoginResult[1].toString())
    }
    //</editor-fold>


    //<editor-fold desc="Validations Methods>"
    private fun getAvalidInput(inputText: EditText): String? =
        if (inputText.text.toString().isNotEmpty() || inputText.text.toString().isNotBlank()
        ) inputText.text.toString().trim() else null

    private fun isValidId(inputId: String,responseId:String?): Boolean = inputId.equals("${responseId}medical",true)

    private fun isvalidPassWord(id: String?, password: String): Boolean = password.equals("${id}cite",true)
    //</editor-fold>


}

