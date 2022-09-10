package com.example.coolblueassignment.framework.dto

import com.example.coolblueassignment.entities.Product
import com.squareup.moshi.Json

data class ProductDTO(
    @Json(name = "productId")
    val productId: Long,
    @Json(name = "productName")
    val productName: String,
    @Json(name = "reviewInformation")
    val reviewInformation: ReviewInformationDTO,
    @Json(name = "USPs")
    val usps: List<String>,
    @Json(name = "availabilityState")
    val availabilityState: Int,
    @Json(name = "salesPriceIncVat")
    val salesPriceIncVat: Int,
    @Json(name = "productImage")
    val productImage: String,
    @Json(name = "nextDayDelivery")
    val nextDayDelivery: Boolean,
)

fun ProductDTO.toDomainEntity(): Product {
    return Product(
        id = productId,
        name = productName,
        reviewInformation = reviewInformation.toDomainEntity(),
        usps = usps,
        numberOfStoresAvailable = availabilityState,
        salesPriceIncVat = salesPriceIncVat,
        imageUrl = productImage,
        hasNextDayDelivery = nextDayDelivery,
    )
}
