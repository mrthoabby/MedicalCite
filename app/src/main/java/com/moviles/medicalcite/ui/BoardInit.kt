package com.moviles.medicalcite.ui

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import com.moviles.medicalcite.*
import com.moviles.medicalcite.`in`.DataManager
import com.moviles.medicalcite.`in`.MedicalDatabaseHelper
import com.moviles.medicalcite.ui.viewmodels.BoardInitActivityViewModel
import kotlinx.android.synthetic.main.activity_board_init.*

class BoardInit : AppCompatActivity() {
    //<editor-fold desc="Define Properties">
    private var nameUser: String = EMPTY_DEFAULT_VALUE
    private var idUser: Int = 0
    private lateinit var medicalDataHelper: MedicalDatabaseHelper
    private lateinit var listContent: Array<Specialization>
    private val viewModel by lazy { ViewModelProviders.of(this)[BoardInitActivityViewModel::class.java] }
    //</editor-fold>

    //<editor-fold desc="Override Methods">
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board_init)

        //Define code settings
        medicalDataHelper = MedicalDatabaseHelper(this)
        listContent = DataManager.getSpecializations(medicalDataHelper)
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


        //Define View settings
        inicializeDisplayValues()

        //Define View Events
        btnAddCite.setOnClickListener {
            val currentSpinnerValue: Specialization = spinnerShowCites.selectedItem as Specialization
            goToSendNewIntent(currentSpinnerValue.specializationid)
        }

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
        txtShowName.text = nameUser
        txtShowNumberId.text = idUser.toString()
        spinnerShowCites.adapter =
            ArrayAdapter(this, android.R.layout.simple_list_item_1, listContent)
    }

    override fun onBackPressed() {
        //super.onBackPressed()
    }
}