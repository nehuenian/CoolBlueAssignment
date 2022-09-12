package com.example.coolblueassignment.entities

data class ProductsPage(
    val products: List<Product>,
    val currentPage: Int,
    val lastPage: Int
)
