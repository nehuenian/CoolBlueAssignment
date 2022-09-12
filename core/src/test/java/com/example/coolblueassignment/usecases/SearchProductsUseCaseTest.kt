package com.example.coolblueassignment.usecases

import com.example.coolblueassignment.data.TestProductRepository
import com.example.coolblueassignment.entities.OperationResult
import com.example.coolblueassignment.entities.ProductsPage
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class SearchProductsUseCaseTest {

    private val productRepository = TestProductRepository()
    private val flowOfOperationResult = SearchProductsUseCase(productRepository)

    @Test
    fun `GIVEN a query and a page number WHEN the searchProduct operation is invoked AND the search is successful THEN a successful operation result with a product page should be received`() {
        // GIVEN
        productRepository.shouldReturnError = false
        val query = SEARCH_QUERY
        val pageNumber = FIRST_PAGE

        // WHEN
        val operationResult = runBlocking {
            flowOfOperationResult(query, pageNumber)
        }

        // THEN
        assertTrue(operationResult is OperationResult.Success<ProductsPage>)
        (operationResult as OperationResult.Success<ProductsPage>).run {
            assertNotNull(data.products)
            assertNotNull(data.currentPage)
            assertNotNull(data.lastPage)
        }
    }

    @Test
    fun `GIVEN a query and a page number WHEN the searchProduct operation is invoked AND the search is a failure THEN an error operation result with a throwable should be received`() {
        // GIVEN
        productRepository.shouldReturnError = true
        val query = SEARCH_QUERY
        val pageNumber = FIRST_PAGE

        // WHEN
        val operationResult = runBlocking {
            flowOfOperationResult(query, pageNumber)
        }

        // THEN
        assertTrue(operationResult is OperationResult.Error)
        (operationResult as OperationResult.Error).run {
            assertNotNull(throwable)
        }
    }

    private companion object {
        const val SEARCH_QUERY = "A query for a product"
        const val FIRST_PAGE = 1
    }
}