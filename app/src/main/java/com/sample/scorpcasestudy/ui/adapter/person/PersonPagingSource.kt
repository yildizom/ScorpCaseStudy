package com.sample.scorpcasestudy.ui.adapter.person

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sample.scorpcasestudy.data.DataSource
import com.sample.scorpcasestudy.data.Person
import kotlinx.coroutines.suspendCancellableCoroutine
import java.lang.Exception

class PersonPagingSource(private val dataSource: DataSource): PagingSource<String, Person>() {
    override suspend fun load(params: LoadParams<String>): LoadResult<String, Person> {
        return try {
            val nextKey = params.key
            fetchData(nextKey)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<String, Person>): String? {
        return null
    }

    private suspend fun fetchData(nextKey: String?): LoadResult<String, Person> =
        suspendCancellableCoroutine { continuation ->
            dataSource.fetch(nextKey) { fetchResponse, fetchError ->
                if (fetchError != null) {
                    val e = Exception(fetchError.errorDescription)
                    val error = LoadResult.Error<String, Person>(e)
                    Log.w("fetchError", fetchError.errorDescription)
                    continuation.resume(error, null)
                } else {
                    val data = fetchResponse?.people ?: listOf()
                    continuation.resume(
                        if (data.isNullOrEmpty()) {
                            LoadResult.Error(Exception("No data"))
                        } else {
                            LoadResult.Page(
                                data = data,
                                prevKey = null,
                                nextKey = fetchResponse?.next
                            )
                        }, null)
                }
            }
        }
}