package com.example.coolblueassignment.framework.dto

import com.example.coolblueassignment.entities.ReviewSummary
import com.squareup.moshi.Json

data class ReviewSummaryDTO(
    @Json(name = "reviewAverage")
    val reviewAverage: Double,
    @Json(name = "reviewCount")
    val reviewCount: Int,
)

fun ReviewSummaryDTO.toDomainEntity(): ReviewSummary {
    return ReviewSummary(
        average = reviewAverage,
        count = reviewCount,
    )
}
