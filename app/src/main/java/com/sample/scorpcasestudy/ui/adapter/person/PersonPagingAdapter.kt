package com.sample.scorpcasestudy.ui.adapter.person

import com.sample.scorpcasestudy.data.Person
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.sample.scorpcasestudy.R

class PersonPagingAdapter: PagingDataAdapter<Person, PersonViewHolder>(diffCallback) {
    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_person, parent, false)
        return PersonViewHolder(view)
    }

    companion object {
        val diffCallback = object: DiffUtil.ItemCallback<Person>() {
            override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean {
                return oldItem == newItem
            }
        }
    }
}