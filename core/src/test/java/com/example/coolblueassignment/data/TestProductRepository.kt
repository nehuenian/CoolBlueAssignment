package com.example.coolblueassignment.data

import com.example.coolblueassignment.entities.OperationResult
import com.example.coolblueassignment.entities.Product
import com.example.coolblueassignment.entities.ProductsPage

class TestProductRepository : ProductRepository {

    var shouldReturnError = false
    var productList: List<Product> = ArrayList()

    override suspend fun getProductsPage(
        query: String,
        pageNumber: Int
    ): OperationResult<ProductsPage> {
        return if (shouldReturnError) {
            OperationResult.Error(Throwable(ERROR_MESSAGE))
        } else {
            OperationResult.Success(ProductsPage(productList, pageNumber, FINAL_PAGE))
        }
    }

    companion object {
        const val ERROR_MESSAGE = "Some error message"
        const val FINAL_PAGE = 3
    }

}