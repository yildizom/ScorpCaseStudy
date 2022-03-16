package com.sample.scorpcasestudy.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.sample.scorpcasestudy.data.DataSource
import com.sample.scorpcasestudy.databinding.ActivityMainBinding
import com.sample.scorpcasestudy.ui.adapter.person.PersonPagingAdapter
import com.sample.scorpcasestudy.ui.adapter.state.PersonLoadStateAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity: AppCompatActivity(), DependencyProvider {

    override fun getDataSource(): DataSource = DataSource()

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    private val personAdapter = PersonPagingAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        viewModel =
            ViewModelProvider(this, MainViewModelFactory(this)).get(MainViewModel::class.java)
        setContentView(binding.root)
        initViews()

        lifecycleScope.launch {
            viewModel.items.collectLatest { personAdapter.submitData(it) }
        }
    }

    private fun initViews() {
        binding.apply {
            recyclerView.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                adapter =
                    this@MainActivity.personAdapter.withLoadStateFooter(PersonLoadStateAdapter { personAdapter.retry() })
            }

            personAdapter.addLoadStateListener {
                it.source.refresh.let { state ->
                    progressListLoading.isVisible = state is LoadState.Loading
                    swipeRefreshLayout.isVisible = state is LoadState.NotLoading
                    buttonRetry.isVisible = state is LoadState.Error
                    swipeRefreshLayout.isRefreshing = state is LoadState.Loading

                    if (state is LoadState.Error) {
                        Toast.makeText(
                            this@MainActivity,
                            "${state.error.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }

            buttonRetry.setOnClickListener {
                personAdapter.retry()
            }

            swipeRefreshLayout.setOnRefreshListener {
                personAdapter.refresh()
            }
        }
    }
}