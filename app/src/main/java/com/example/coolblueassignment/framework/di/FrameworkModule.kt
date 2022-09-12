package com.example.coolblueassignment.framework.di

import com.example.coolblueassignment.framework.api.ProductAPI
import com.example.coolblueassignment.framework.data.source.ProductDataSource
import com.example.coolblueassignment.framework.data.source.RemoteProductDataSourceImpl
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FrameworkModule {
    private val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(ProductAPI.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

    @Provides
    @Singleton
    fun provideProductApi(retrofit: Retrofit): ProductAPI =
        retrofit.create(ProductAPI::class.java)

    @Provides
    @Singleton
    fun provideLocalProductDataSource(productAPI: ProductAPI): ProductDataSource {
        return RemoteProductDataSourceImpl(productAPI)
    }
}