package com.example.coolblueassignment.presentation.viewmodel

import com.example.coolblueassignment.testutil.CoroutinesRule
import com.example.coolblueassignment.testutil.SEARCH_QUERY
import com.example.coolblueassignment.testutil.SEARCH_QUERY_2
import com.example.coolblueassignment.testutil.TestProductRepository
import com.example.coolblueassignment.usecases.SearchProductsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ProductSearchViewModelTest {
    @get:Rule
    var coroutinesRule = CoroutinesRule()

    private val repository = TestProductRepository()
    private val searchProductsUseCase = SearchProductsUseCase(repository)
    private val viewModel = ProductSearchViewModel(searchProductsUseCase)

    @Test
    fun `GIVEN the initial empty query WHEN the search product stream is being listened THEN the initial page should be received`() {
        runBlocking {
            launch(Dispatchers.Main) {
                // GIVEN

                // WHEN
                val firstResult = viewModel.productListResultStream.take(1)

                // THEN
                firstResult.collect { pagingData ->
                    assertNotNull(pagingData)
                }
            }
        }
    }

    @Test
    fun `GIVEN a new query WHEN the new query is injected THEN a new page should be received`() {
        runBlocking {
            launch(Dispatchers.Main) {
                // GIVEN
                val firstQuery = SEARCH_QUERY
                val secondQuery = SEARCH_QUERY_2

                // WHEN
                viewModel.updateSearchQuery(firstQuery)
                val firstResult = viewModel.productListResultStream.first()
                viewModel.updateSearchQuery(secondQuery)
                val secondResult = viewModel.productListResultStream.first()


                // THEN
                assertNotNull(firstResult)
                assertNotNull(secondResult)
            }
        }
    }
}