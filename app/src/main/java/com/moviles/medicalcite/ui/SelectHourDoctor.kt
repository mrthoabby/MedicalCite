package com.moviles.medicalcite.ui

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.moviles.medicalcite.*
import com.moviles.medicalcite.`in`.DataManager
import com.moviles.medicalcite.`in`.MedicalDatabaseHelper
import com.moviles.medicalcite.ui.adapter.MedicalRecyClerAdapter
import com.moviles.medicalcite.ui.viewmodels.SelectHorDoctorActivityViewModel
import kotlinx.android.synthetic.main.activity_select_hour_doctor.*

class SelectHourDoctor : AppCompatActivity() {
    //<editor-fold desc="Define Properties">
    private lateinit var medicalDataHelper: MedicalDatabaseHelper
    private var specializationId = 0
    private var idUser = 0
    private var nameUser = ""
    private lateinit var listContent: Array<CiteMedical>
    private val viewModel by lazy { ViewModelProviders.of(this)[SelectHorDoctorActivityViewModel::class.java] }

    //</editor-fold>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_hour_doctor)

        //Define code settings
        medicalDataHelper = MedicalDatabaseHelper(this)

        if (viewModel.isActive) {
            idUser = viewModel.idCurrentUserLoged
            nameUser = viewModel.nameUserCurrent
            specializationId = viewModel.specializationId
        } else {
            nameUser = intent.getStringExtra(EXTRA_NAME_LOGIN).toString()
            idUser = intent.getIntExtra(EXTRA_ID_LOGIN, idUser)
            specializationId = intent.getIntExtra(EXTRA_ID_SPECIALIZATION, specializationId)
            viewModel.idCurrentUserLoged = idUser
            viewModel.nameUserCurrent = nameUser
            viewModel.specializationId = specializationId
            viewModel.isActive = true
        }
        listContent = DataManager.getMedicalCiteAviables(specializationId, medicalDataHelper)
        recyclerSelectorCiteDoctor.layoutManager = LinearLayoutManager(this)
        recyclerSelectorCiteDoctor.adapter = MedicalRecyClerAdapter(this, listContent)
        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = prefs.edit()
        editor.putInt(EXTRA_ID_LOGIN, idUser)
        editor.apply()
    }

    override fun onBackPressed() {
        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = prefs.edit()
        editor.remove(EXTRA_ID_LOGIN)
        super.onBackPressed()

    }

    override fun onDestroy() {
        medicalDataHelper.close()
        super.onDestroy()
    }
}