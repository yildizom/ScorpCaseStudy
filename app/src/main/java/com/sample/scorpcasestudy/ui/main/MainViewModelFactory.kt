package com.sample.scorpcasestudy.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainViewModelFactory(private val dependencyProvider: DependencyProvider):
    ViewModelProvider.Factory {
    override fun <T: ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(dependencyProvider) as T
        }
        throw java.lang.IllegalArgumentException("Unknown ViewModel class")
    }
}