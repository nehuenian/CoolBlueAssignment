package com.example.coolblueassignment.presentation.models

import com.example.coolblueassignment.entities.Product

data class ProductItem(
    val id: Long,
    val name: String,
    val averageReview: Double,
    val reviewsCounter: Int,
    val usps: List<String>,
    val numberOfStoresAvailable: Int,
    val price: Double,
    val imageUrl: String,
    val hasNextDayDelivery: Boolean,
) {
    constructor(product: Product) : this(
        id = product.id,
        name = product.name,
        averageReview = product.reviewInformation.summary.average,
        reviewsCounter = product.reviewInformation.summary.count,
        usps = product.usps,
        numberOfStoresAvailable = product.numberOfStoresAvailable,
        price = product.salesPriceIncVat,
        imageUrl = product.imageUrl,
        hasNextDayDelivery = product.hasNextDayDelivery
    )
}
