package com.example.coolblueassignment.presentation.paging

import androidx.paging.PagingSource
import com.example.coolblueassignment.presentation.ui.adapters.models.ProductItem
import com.example.coolblueassignment.testutil.*
import com.example.coolblueassignment.usecases.SearchProductsUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class ProductsPagingSourceTest {
    private val productRepository = TestProductRepository()
    private val searchProducts = SearchProductsUseCase(productRepository)
    private val pagingSource = ProductsPagingSource(searchProducts, SEARCH_QUERY)

    @Test
    fun `GIVEN a page refresh request WHEN the page is loaded THEN the first page should be returned with no prev page key`() {
        // GIVEN
        val loadRequest = PagingSource.LoadParams.Refresh<Int>(
            key = null,
            loadSize = IGNORED_PAGE_SIZE,
            placeholdersEnabled = false,
        )

        // WHEN
        val newPage = runBlocking {
            pagingSource.load(loadRequest)
        }

        // THEN
        assertEquals(
            PagingSource.LoadResult.Page(
                data = mockedListOfFirstPageProducts.map { ProductItem(it) },
                prevKey = null,
                nextKey = SECOND_PAGE
            ),
            newPage,
        )
    }

    @Test
    fun `GIVEN a next page request after the first page WHEN the page is loaded THEN the second page should be returned`() {
        // GIVEN
        val loadRequest = PagingSource.LoadParams.Append<Int>(
            SECOND_PAGE,
            loadSize = IGNORED_PAGE_SIZE,
            placeholdersEnabled = false,
        )

        // WHEN
        val newPage = runBlocking {
            pagingSource.load(loadRequest)
        }

        // THEN
        assertEquals(
            PagingSource.LoadResult.Page(
                data = mockedListOfSecondPageProducts.map { ProductItem(it) },
                prevKey = FIRST_PAGE,
                nextKey = THIRD_PAGE
            ),
            newPage,
        )
    }

    @Test
    fun `GIVEN a next page request after the second page WHEN the page is loaded THEN the third page should be returned with no next page key`() {
        // GIVEN
        val loadRequest = PagingSource.LoadParams.Append<Int>(
            THIRD_PAGE,
            loadSize = IGNORED_PAGE_SIZE,
            placeholdersEnabled = false,
        )

        // WHEN
        val newPage = runBlocking {
            pagingSource.load(loadRequest)
        }

        // THEN
        assertEquals(
            PagingSource.LoadResult.Page(
                data = mockedListOfThirdPageProducts.map { ProductItem(it) },
                prevKey = SECOND_PAGE,
                nextKey = null
            ),
            newPage,
        )
    }

    @Test
    fun `GIVEN a prev page request after the second page WHEN the page is loaded THEN the first page should be returned with no prev page key`() {
        // GIVEN
        val loadRequest = PagingSource.LoadParams.Prepend<Int>(
            key = FIRST_PAGE,
            loadSize = IGNORED_PAGE_SIZE,
            placeholdersEnabled = false,
        )

        // WHEN
        val newPage = runBlocking {
            pagingSource.load(loadRequest)
        }

        // THEN
        assertEquals(
            PagingSource.LoadResult.Page(
                data = mockedListOfFirstPageProducts.map { ProductItem(it) },
                prevKey = null,
                nextKey = SECOND_PAGE
            ),
            newPage,
        )
    }

    @Test
    fun `GIVEN a page refresh request WHEN the page is loaded with a fail in the use case operation THEN a load error should be returned with the error throwable from the operation result`() {
        // GIVEN
        productRepository.shouldReturnError = true
        val loadRequest = PagingSource.LoadParams.Refresh<Int>(
            key = null,
            loadSize = IGNORED_PAGE_SIZE,
            placeholdersEnabled = false,
        )

        // WHEN
        val newLoad = runBlocking {
            pagingSource.load(loadRequest)
        }

        // THEN
        assertEquals(
            PagingSource.LoadResult.Error<Int, Throwable>(throwableOperationError),
            newLoad,
        )
    }

    private companion object {
        const val IGNORED_PAGE_SIZE = 2
    }
}