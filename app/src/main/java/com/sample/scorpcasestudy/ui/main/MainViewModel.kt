package com.sample.scorpcasestudy.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.sample.scorpcasestudy.ui.adapter.person.PersonPagingSource

class MainViewModel(private val dependencyProvider: DependencyProvider): ViewModel() {
    val items = Pager(
        PagingConfig(
            pageSize = 20,
        )
    ) {
        PersonPagingSource(dependencyProvider.getDataSource())
    }.flow.cachedIn(viewModelScope)
}