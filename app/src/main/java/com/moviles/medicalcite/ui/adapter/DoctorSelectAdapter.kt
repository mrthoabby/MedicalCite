package com.moviles.medicalcite.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.moviles.medicalcite.R
import com.moviles.medicalcite.UserClient

class DoctorSelectAdapter(
    context: Context,
    private val medicalPatients: Array<UserClient>
) : RecyclerView.Adapter<DoctorSelectAdapter.ViewHolder>() {
    private val layoutInflater = LayoutInflater.from(context)

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        val tipeCite = itemView?.findViewById<TextView?>(R.id.tipeCite)
        val nameSelect  = itemView?.findViewById<TextView?>(R.id.nameSelect)
        val dateCite = itemView?.findViewById<TextView?>(R.id.datecite)
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val itemView = layoutInflater.inflate(R.layout.list_users_selecdoctor, p0, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return medicalPatients.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val patient = medicalPatients[p1]
        p0.tipeCite?.text = patient.specializationName
        p0.nameSelect?.text = patient.nameUser
        p0.dateCite?.text = patient.dateCite
    }
}