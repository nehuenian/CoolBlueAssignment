package com.example.coolblueassignment.framework.data.source

import com.example.coolblueassignment.framework.api.ProductAPI
import com.example.coolblueassignment.framework.dto.ProductsResponseDTO
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteProductDataSourceImpl @Inject constructor(
    private val productAPI: ProductAPI
) : ProductDataSource {
    override suspend fun getProductsPageResponse(
        query: String,
        pageNumber: Int
    ): ProductsResponseDTO {
        return if (query == ERROR_QUERY) {
            productAPI.searchProductsWithFailure(query, pageNumber)
        } else {
            productAPI.searchProducts(query, pageNumber)
        }
    }

    private companion object {
        const val ERROR_QUERY = "error"
    }
}