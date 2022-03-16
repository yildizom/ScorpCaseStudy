package com.sample.scorpcasestudy.ui.adapter.state

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter

class PersonLoadStateAdapter(private val retry: () -> Unit):
    LoadStateAdapter<PersonLoadStateViewHolder>() {
    override fun onBindViewHolder(holder: PersonLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): PersonLoadStateViewHolder {
        return PersonLoadStateViewHolder(retry, parent)
    }
}