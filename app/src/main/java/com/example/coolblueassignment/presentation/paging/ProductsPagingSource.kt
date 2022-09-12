package com.example.coolblueassignment.presentation.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.coolblueassignment.entities.OperationResult
import com.example.coolblueassignment.entities.ProductsPage
import com.example.coolblueassignment.presentation.models.ProductItem
import com.example.coolblueassignment.usecases.SearchProductsUseCase

class ProductsPagingSource(
    private val searchProducts: SearchProductsUseCase,
    private val query: String,
) : PagingSource<Int, ProductItem>() {

    override fun getRefreshKey(state: PagingState<Int, ProductItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(ONE_POSITION)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(ONE_POSITION)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ProductItem> {
        val pageIndex = params.key ?: STARTING_PAGE_INDEX
        return when (val operationResult = searchProducts(query, pageIndex)) {
            is OperationResult.Success -> {
                operationResult.data.let { productsPage ->
                    LoadResult.Page(
                        data = productsPage.products.map { ProductItem(it) },
                        prevKey = getPrevKey(productsPage),
                        nextKey = getNextKey(productsPage)
                    )
                }
            }
            is OperationResult.Error -> LoadResult.Error(operationResult.throwable)
        }
    }

    private fun getPrevKey(productsPage: ProductsPage): Int? {
        return productsPage.currentPage.minus(ONE_POSITION).takeIf { prevPageKey ->
            prevPageKey >= STARTING_PAGE_INDEX
        }
    }

    private fun getNextKey(
        productsPage: ProductsPage
    ): Int? {
        return productsPage.currentPage.plus(ONE_POSITION).takeIf { nextPageKey ->
            nextPageKey <= productsPage.lastPage
        }
    }

    private companion object {
        const val ONE_POSITION = 1
        const val STARTING_PAGE_INDEX = 1
    }
}