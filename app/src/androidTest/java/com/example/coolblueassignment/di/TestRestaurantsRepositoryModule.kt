package com.example.coolblueassignment.testutil

import android.content.Context
import com.example.coolblueassignment.data.ProductRepository
import com.example.coolblueassignment.data.TestProductRepository
import com.example.coolblueassignment.framework.di.RepositoryModule
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [RepositoryModule::class]
)
object TestProductRepositoryModule {
    @Singleton
    @Provides
    fun provideProductRepository(@ApplicationContext context: Context): ProductRepository {
        return TestProductRepository(context)
    }
}