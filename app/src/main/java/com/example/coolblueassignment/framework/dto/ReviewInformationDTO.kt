package com.example.coolblueassignment.framework.dto

import com.example.coolblueassignment.entities.ReviewInformation
import com.squareup.moshi.Json

data class ReviewInformationDTO(
    @Json(name = "reviews")
    val reviews: List<String>,
    @Json(name = "reviewSummary")
    val reviewSummary: ReviewSummaryDTO,
)

fun ReviewInformationDTO.toDomainEntity(): ReviewInformation {
    return ReviewInformation(
        reviews = reviews,
        summary = reviewSummary.toDomainEntity(),
    )
}
