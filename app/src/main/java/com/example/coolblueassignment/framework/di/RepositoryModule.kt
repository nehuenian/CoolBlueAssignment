package com.example.coolblueassignment.framework.di

import com.example.coolblueassignment.data.ProductRepository
import com.example.coolblueassignment.framework.data.ProductRepositoryImpl
import com.example.coolblueassignment.framework.data.source.ProductDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideProductRepository(productDataSource: ProductDataSource): ProductRepository {
        return ProductRepositoryImpl(productDataSource)
    }
}