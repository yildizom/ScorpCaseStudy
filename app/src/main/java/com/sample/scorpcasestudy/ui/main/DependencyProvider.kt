package com.sample.scorpcasestudy.ui.main

import com.sample.scorpcasestudy.data.DataSource

interface DependencyProvider {
    fun getDataSource(): DataSource
}