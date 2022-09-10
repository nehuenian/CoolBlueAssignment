package com.example.coolblueassignment.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.coolblueassignment.framework.data.paging.ProductsPagingSource
import com.example.coolblueassignment.presentation.models.ProductItem
import com.example.coolblueassignment.usecases.SearchProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.transformLatest
import javax.inject.Inject

@HiltViewModel
class ProductSearchViewModel @Inject constructor(
    private val searchProducts: SearchProductsUseCase
) : ViewModel() {
    private val query = MutableStateFlow("")

    @ExperimentalCoroutinesApi
    val productListResultStream: Flow<PagingData<ProductItem>> =
        query.transformLatest { latestQuery ->
            Pager(
                config = PagingConfig(pageSize = 20),
                pagingSourceFactory = { ProductsPagingSource(searchProducts, latestQuery) }
            ).flow
        }

    fun updateSearchQuery(newQuery: String) {
        query.value = newQuery
    }
}