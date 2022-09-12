package com.example.coolblueassignment.testutil

import com.example.coolblueassignment.data.ProductRepository
import com.example.coolblueassignment.entities.OperationResult
import com.example.coolblueassignment.entities.ProductsPage

class TestProductRepository : ProductRepository {

    var shouldReturnError = false

    override suspend fun getProductsPage(
        query: String,
        pageNumber: Int
    ): OperationResult<ProductsPage> {
        return if (shouldReturnError) {
            OperationResult.Error(throwableOperationError)
        } else {
            when (pageNumber) {
                FIRST_PAGE -> OperationResult.Success(mockedFirstPage)
                SECOND_PAGE -> OperationResult.Success(mockedSecondPage)
                THIRD_PAGE -> OperationResult.Success(mockedThirdPage)
                else -> OperationResult.Error(throwableOperationError)
            }
        }
    }
}