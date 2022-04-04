package com.moviles.medicalcite.ui

import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.Toast
import com.moviles.medicalcite.*
import com.moviles.medicalcite.`in`.DataManager
import com.moviles.medicalcite.`in`.MedicalDatabaseHelper
import com.moviles.medicalcite.ui.viewmodels.SucessActivityViewModel
import kotlinx.android.synthetic.main.activity_board_init.*
import kotlinx.android.synthetic.main.activity_sucess.*

class SucessActivity : AppCompatActivity() {

    //<editor-fold desc="Define Properties">
    private var specializationId: Int = 0
    private var dateid: Int = 0
    private lateinit var medicalDataHelper: MedicalDatabaseHelper
    private val viewModel by lazy { ViewModelProviders.of(this)[SucessActivityViewModel::class.java] }

    //</editor-fold>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sucess)

        //Define code settings
        medicalDataHelper = MedicalDatabaseHelper(this)
        if (viewModel.isActive) {
            specializationId = viewModel.specializationId
            dateid = viewModel.dateciteid
        } else {
            specializationId = intent.getIntExtra(EXTRA_ID_SPECIALIZATION,specializationId)
            dateid = intent.getIntExtra(EXTRA_ID_NOTE,specializationId)
            viewModel.specializationId = specializationId
            viewModel.dateciteid = dateid
            viewModel.isActive = true
        }
        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = prefs.edit()
        var result  = DataManager.getMedicalCite(dateid,medicalDataHelper)
        var resultIdUser = prefs.getInt(EXTRA_ID_LOGIN,0)
        var isUpdate =DataManager.toAsignCite(resultIdUser,dateid,medicalDataHelper)
        if(isUpdate){
            Toast.makeText(this, R.string.showSucces, Toast.LENGTH_SHORT).show()
            inicializeDisplayValues(result)
        }
        else
        {
            Toast.makeText(this, "No fue posible añadir el registro", Toast.LENGTH_SHORT).show()
        }
        //Define View settings

        //Define View Events

    }

    private fun inicializeDisplayValues(result:CiteMedical?) {
        doctor.text = result?.nameDoctor
        date.text = result?.dateAviable
        cite.text = result?.specializationName
    }

    override fun onDestroy() {
        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = prefs.edit()
        medicalDataHelper.close()
        editor.remove(EXTRA_ID_LOGIN)
        super.onDestroy()
    }

    override fun onBackPressed() {
        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = prefs.edit()
        editor.remove(EXTRA_ID_LOGIN)
        super.onBackPressed()
    }
}