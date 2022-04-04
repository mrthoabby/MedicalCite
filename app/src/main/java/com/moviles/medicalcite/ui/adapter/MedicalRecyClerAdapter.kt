package com.moviles.medicalcite.ui.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.moviles.medicalcite.CiteMedical
import com.moviles.medicalcite.EXTRA_ID_NOTE
import com.moviles.medicalcite.EXTRA_ID_SPECIALIZATION
import com.moviles.medicalcite.R
import com.moviles.medicalcite.ui.SucessActivity
import kotlin.coroutines.coroutineContext

class MedicalRecyClerAdapter(
    private val context: Context,
    private val medicalCites: Array<CiteMedical>
) : RecyclerView.Adapter<MedicalRecyClerAdapter.ViewHolder>() {
    private val layoutInflater = LayoutInflater.from(context)

    inner class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        val nameDoctor = itemView?.findViewById<TextView?>(R.id.idNameSelectorDoctor)
        val nameSpecialization = itemView?.findViewById<TextView?>(R.id.txtInfoSelectorDoctor)
        val dateCiteDoctor = itemView?.findViewById<TextView?>(R.id.txtDateSelectorDoctor)
        var positionMedical = 0
        var positionMedicalOther = 0
        var definitiva = 0
        init {
            itemView?.setOnClickListener{
                val intent = Intent(context, SucessActivity::class.java)
                intent.putExtra(EXTRA_ID_SPECIALIZATION,positionMedical)
                intent.putExtra(EXTRA_ID_NOTE,definitiva)
                context.startActivity(intent)
            }
        }
        //val citePosition = 0
        //var sameCite:CiteMedical? = null
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val itemView = layoutInflater.inflate(R.layout.item_selectordoctorcite_list, p0, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val citeMedical = medicalCites[p1]
        p0.nameDoctor?.text = citeMedical.nameDoctor
        p0.nameSpecialization?.text = citeMedical.specializationName
        p0.dateCiteDoctor?.text = citeMedical.dateAviable
        p0.positionMedical = citeMedical.specializationid
        p0.positionMedicalOther = p1
        p0.definitiva = citeMedical.dateId

    }

    override fun getItemCount(): Int = medicalCites.size

}