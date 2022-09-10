package com.example.coolblueassignment.presentation.di

import com.example.coolblueassignment.data.ProductRepository
import com.example.coolblueassignment.usecases.SearchProductsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    fun provideSearchProductsUseCase(repository: ProductRepository) =
        SearchProductsUseCase(repository)
}