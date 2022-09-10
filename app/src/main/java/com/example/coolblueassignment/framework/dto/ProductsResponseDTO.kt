package com.example.coolblueassignment.framework.dto

import com.example.coolblueassignment.entities.ProductsPage
import com.squareup.moshi.Json

data class ProductsResponseDTO(
    @Json(name = "products")
    val products: List<ProductDTO>,
    @Json(name = "currentPage")
    val currentPage: Int,
    @Json(name = "pageSize")
    val pageSize: Int,
    @Json(name = "totalResults")
    val totalResults: Int,
    @Json(name = "totalResults")
    val pageCount: Int,
)

fun ProductsResponseDTO.toDomainEntity(): ProductsPage {
    return ProductsPage(
        products = products.map { it.toDomainEntity() },
        currentPage = currentPage,
        finalPage = pageCount,
    )
}
