package com.moviles.medicalcite.ui

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.moviles.medicalcite.*
import com.moviles.medicalcite.`in`.DataManager
import com.moviles.medicalcite.`in`.MedicalDatabaseHelper
import com.moviles.medicalcite.ui.adapter.DoctorSelectAdapter
import com.moviles.medicalcite.ui.viewmodels.SelectDoctorActivityViewModel
import kotlinx.android.synthetic.main.activity_select_doctor.*

class SelectDoctor : AppCompatActivity() {
    //<editor-fold desc="Define Properties">
    private var nameUser: String = EMPTY_DEFAULT_VALUE
    private var idUser: Int = 0
    private lateinit var medicalDataHelper: MedicalDatabaseHelper
    private lateinit var listContent: Array<UserClient>
    private val viewModel by lazy { ViewModelProviders.of(this)[SelectDoctorActivityViewModel::class.java] }
    //</editor-fold>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_doctor)

        //Define code settings
        medicalDataHelper = MedicalDatabaseHelper(this)
        if (viewModel.isActive) {
            idUser = viewModel.idCurrentUserLoged
            nameUser = viewModel.nameUserCurrent
        } else {
            nameUser = intent.getStringExtra(EXTRA_NAME_LOGIN).toString()
            idUser = intent.getIntExtra(EXTRA_ID_LOGIN, idUser)
            viewModel.idCurrentUserLoged = idUser
            viewModel.nameUserCurrent = nameUser
            viewModel.isActive = true
        }
        listContent = DataManager.getClientsFromDoctor(idUser,medicalDataHelper)
        listCurrentUsers.layoutManager = LinearLayoutManager(this)
        listCurrentUsers.adapter = DoctorSelectAdapter(this,listContent)


        //Define View settings
        inicializeDisplayValues()

    }

    private fun goToSendNewIntent(currentCite: Int?) {
        val activity = Intent(this, SelectHourDoctor::class.java)
        activity.putExtra(EXTRA_ID_SPECIALIZATION, currentCite)
        activity.putExtra(EXTRA_NAME_LOGIN, nameUser)
        activity.putExtra(EXTRA_ID_LOGIN, idUser)
        startActivity(activity)
    }

    override fun onDestroy() {
        medicalDataHelper.close()
        super.onDestroy()
    }

    private fun inicializeDisplayValues() {
        txtNameDoctor.text = nameUser
       // lisCurrentUsers.adapter = ArrayAdapter(this,)

    }


}