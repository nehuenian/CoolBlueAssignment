package com.example.coolblueassignment.usecases

import com.example.coolblueassignment.data.ProductRepository
import com.example.coolblueassignment.entities.OperationResult
import com.example.coolblueassignment.entities.ProductsPage

class SearchProductsUseCase(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(query: String, pageNumber: Int): OperationResult<ProductsPage> =
        productRepository.getProductsPage(query, pageNumber)
}