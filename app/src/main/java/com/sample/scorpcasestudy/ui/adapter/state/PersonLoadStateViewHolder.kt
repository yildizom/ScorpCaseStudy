package com.sample.scorpcasestudy.ui.adapter.state

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.sample.scorpcasestudy.R

class PersonLoadStateViewHolder(private val retry: () -> Unit, parent: ViewGroup):
    RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_loading_state, parent, false)
    ) {

    fun bind(state: LoadState) {
        val button = itemView.findViewById<Button>(R.id.button_state_retry)
        val progress = itemView.findViewById<ProgressBar>(R.id.progress_state_loading)
        val text = itemView.findViewById<TextView>(R.id.tv_state_error)

        button.setOnClickListener { retry.invoke() }

        progress.isVisible = state is LoadState.Loading
        button.isVisible = state is LoadState.Error
        text.isVisible = state is LoadState.Error
        text.text = (state as? LoadState.Error)?.error?.message
    }
}