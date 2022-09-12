package com.example.coolblueassignment.data

import android.content.Context
import com.example.coolblueassignment.R
import com.example.coolblueassignment.entities.OperationResult
import com.example.coolblueassignment.entities.ProductsPage
import com.example.coolblueassignment.framework.dto.ProductsResponseDTO
import com.example.coolblueassignment.framework.dto.toDomainEntity
import com.example.coolblueassignment.testutil.*
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import javax.inject.Inject

class TestProductRepository @Inject constructor(context: Context) : ProductRepository {

    var shouldReturnError = false
    private val moshi: Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
    private val firstPage =
        moshi.fromJson<ProductsResponseDTO>(
            context,
            R.raw.products_mocked_response_page_1,
            READER_CHARSET
        )
    private val secondPage =
        moshi.fromJson<ProductsResponseDTO>(
            context,
            R.raw.products_mocked_response_page_2,
            READER_CHARSET
        )
    private val thirdPage =
        moshi.fromJson<ProductsResponseDTO>(
            context,
            R.raw.products_mocked_response_page_3,
            READER_CHARSET
        )

    override suspend fun getProductsPage(
        query: String,
        pageNumber: Int
    ): OperationResult<ProductsPage> {
        return if (shouldReturnError) {
            OperationResult.Error(throwableOperationError)
        } else {
            when (pageNumber) {
                FIRST_PAGE -> OperationResult.Success(firstPage.toDomainEntity())
                SECOND_PAGE -> OperationResult.Success(secondPage.toDomainEntity())
                LAST_PAGE -> OperationResult.Success(thirdPage.toDomainEntity())
                else -> OperationResult.Error(throwableOperationError)
            }
        }
    }
}