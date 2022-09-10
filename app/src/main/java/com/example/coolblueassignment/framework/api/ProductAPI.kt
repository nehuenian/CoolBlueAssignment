package com.example.coolblueassignment.framework.api

import com.example.coolblueassignment.framework.dto.ProductsResponseDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductAPI {
    @GET("mobile-assignment/search")
    suspend fun searchProducts(
        @Query("query") query: String,
        @Query("page") pageNumber: Int,
    ): ProductsResponseDTO

    companion object {
        const val BASE_URL = "https://bdk0sta2n0.execute-api.eu-west-1.amazonaws.com/"
    }
}