package com.example.coolblueassignment.entities

data class Product(
    val id: Long,
    val name: String,
    val reviewInformation: ReviewInformation,
    val usps: List<String>,
    val numberOfStoresAvailable: Int,
    val salesPriceIncVat: Double,
    val imageUrl: String,
    val hasNextDayDelivery: Boolean,
)
