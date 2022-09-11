package com.example.coolblueassignment.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.coolblueassignment.presentation.paging.ProductsPagingSource
import com.example.coolblueassignment.presentation.models.ProductItem
import com.example.coolblueassignment.usecases.SearchProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class ProductSearchViewModel @Inject constructor(
    private val searchProducts: SearchProductsUseCase
) : ViewModel() {
    private val query = MutableStateFlow("")

    @ExperimentalCoroutinesApi
    val productListResultStream: Flow<PagingData<ProductItem>> =
        query.flatMapLatest { latestQuery ->
            Pager(
                config = PagingConfig(pageSize = 20),
                pagingSourceFactory = { ProductsPagingSource(searchProducts, latestQuery) }
            ).flow.cachedIn(viewModelScope)
        }

    fun updateSearchQuery(newQuery: String) {
        if (newQuery != query.value) {
            query.value = newQuery
        }
    }
}