package com.example.coolblueassignment.framework.data

import com.example.coolblueassignment.data.ProductRepository
import com.example.coolblueassignment.entities.OperationResult
import com.example.coolblueassignment.entities.ProductsPage
import com.example.coolblueassignment.framework.data.source.ProductDataSource
import com.example.coolblueassignment.framework.dto.toDomainEntity
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productDataSource: ProductDataSource
) : ProductRepository {
    override suspend fun getProductsPage(
        query: String,
        pageNumber: Int
    ): OperationResult<ProductsPage> {
        return try {
            OperationResult.Success(
                productDataSource.getProductsPageResponse(query, pageNumber).toDomainEntity()
            )
        } catch (exception: IOException) {
            OperationResult.Error(exception)
        } catch (exception: HttpException) {
            OperationResult.Error(exception)
        }
    }
}