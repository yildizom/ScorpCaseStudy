package com.sample.scorpcasestudy.ui.adapter.person

import android.annotation.SuppressLint
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sample.scorpcasestudy.R
import com.sample.scorpcasestudy.data.Person

class PersonViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    @SuppressLint("SetTextI18n")
    fun bind(person: Person?) {
        person?.let {
            itemView.findViewById<TextView>(R.id.tv_item_person).text = "${person.fullName} (${person.id})"
        }
    }
}