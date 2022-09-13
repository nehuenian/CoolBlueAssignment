package com.example.coolblueassignment.presentation.ui.adapters.models

import com.example.coolblueassignment.entities.Product
import com.example.coolblueassignment.presentation.util.toFiveStarsOverFloat

data class ProductItem(
    val id: Long,
    val name: String,
    val averageReview: Float,
    val reviewsCounter: Int,
    val usps: List<String>,
    val numberOfStoresAvailable: Int,
    val price: Double,
    val imageUrl: String,
    val hasNextDayDelivery: Boolean,
) : ProductListItem() {
    constructor(product: Product) : this(
        id = product.id,
        name = product.name,
        averageReview = product.reviewInformation.summary.average.toFiveStarsOverFloat(),
        reviewsCounter = product.reviewInformation.summary.count,
        usps = product.usps,
        numberOfStoresAvailable = product.numberOfStoresAvailable,
        price = product.salesPriceIncVat,
        imageUrl = product.imageUrl,
        hasNextDayDelivery = product.hasNextDayDelivery
    )

    override fun getIdentityValue(): Comparable<*> {
        return id
    }
}
