package com.example.coolblueassignment.framework.data.source

import com.example.coolblueassignment.framework.dto.ProductsResponseDTO

interface ProductDataSource {
    suspend fun getProductsPageResponse(
        query: String,
        pageNumber: Int,
    ): ProductsResponseDTO
}