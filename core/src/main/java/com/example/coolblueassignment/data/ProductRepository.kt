package com.example.coolblueassignment.data

import com.example.coolblueassignment.entities.OperationResult
import com.example.coolblueassignment.entities.ProductsPage

interface ProductRepository {
    suspend fun getProductsPage(query: String, pageNumber: Int): OperationResult<ProductsPage>
}