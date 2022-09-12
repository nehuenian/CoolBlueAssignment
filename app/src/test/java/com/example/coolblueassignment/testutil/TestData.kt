package com.example.coolblueassignment.testutil

import com.example.coolblueassignment.entities.Product
import com.example.coolblueassignment.entities.ProductsPage
import com.example.coolblueassignment.entities.ReviewInformation
import com.example.coolblueassignment.entities.ReviewSummary

const val SEARCH_QUERY = "Search for a product"
const val SEARCH_QUERY_2 = "Search for a new product"

const val FIRST_PAGE = 1
const val SECOND_PAGE = 2
const val THIRD_PAGE = 3

const val LAST_PAGE = THIRD_PAGE

const val ERROR_MESSAGE = "Some error message"

const val FIRST_MOCKED_PRODUCT_ID = 1L
const val FIRST_MOCKED_PRODUCT_NAME = "First product"
const val FIRST_MOCKED_PRODUCT_AVERAGE_REVIEW = 9.3
const val FIRST_MOCKED_PRODUCT_REVIEWS_COUNTER = 94
const val FIRST_MOCKED_PRODUCT_AVAILABILITY = 2
const val FIRST_MOCKED_PRODUCT_PRICE = 59.67
const val FIRST_MOCKED_PRODUCT_IMAGE_URL = "https://image.coolblue.nl/300x750/products/984921"
const val FIRST_MOCKED_PRODUCT_NEXT_DAY_DELIVERY = true

const val SECOND_MOCKED_PRODUCT_ID = 2L
const val SECOND_MOCKED_PRODUCT_NAME = "Second product"
const val SECOND_MOCKED_PRODUCT_AVERAGE_REVIEW = 7.3
const val SECOND_MOCKED_PRODUCT_REVIEWS_COUNTER = 92
const val SECOND_MOCKED_PRODUCT_AVAILABILITY = 127
const val SECOND_MOCKED_PRODUCT_PRICE = 33.67
const val SECOND_MOCKED_PRODUCT_IMAGE_URL = "https://image.coolblue.nl/300x750/products/406340"
const val SECOND_MOCKED_PRODUCT_NEXT_DAY_DELIVERY = false

val throwableOperationError = Throwable(ERROR_MESSAGE)

val firstMockedProductReviews = emptyList<String>()
val firstMockedProductUSPs = emptyList<String>()

val firstMockedProductReviewSummary = ReviewSummary(
    FIRST_MOCKED_PRODUCT_AVERAGE_REVIEW,
    FIRST_MOCKED_PRODUCT_REVIEWS_COUNTER
)

val secondMockedProductReviewSummary = ReviewSummary(
    SECOND_MOCKED_PRODUCT_AVERAGE_REVIEW,
    SECOND_MOCKED_PRODUCT_REVIEWS_COUNTER
)

val firstMockedProductReviewInformation = ReviewInformation(
    firstMockedProductReviews,
    firstMockedProductReviewSummary,
)

val secondMockedProductReviewInformation = ReviewInformation(
    emptyList(),
    secondMockedProductReviewSummary,
)

val firstMockedProduct = Product(
    id = FIRST_MOCKED_PRODUCT_ID,
    name = FIRST_MOCKED_PRODUCT_NAME,
    reviewInformation = firstMockedProductReviewInformation,
    usps = firstMockedProductUSPs,
    numberOfStoresAvailable = FIRST_MOCKED_PRODUCT_AVAILABILITY,
    salesPriceIncVat = FIRST_MOCKED_PRODUCT_PRICE,
    imageUrl = FIRST_MOCKED_PRODUCT_IMAGE_URL,
    hasNextDayDelivery = FIRST_MOCKED_PRODUCT_NEXT_DAY_DELIVERY
)

val secondMockedProduct = Product(
    id = SECOND_MOCKED_PRODUCT_ID,
    name = SECOND_MOCKED_PRODUCT_NAME,
    reviewInformation = secondMockedProductReviewInformation,
    usps = emptyList(),
    numberOfStoresAvailable = SECOND_MOCKED_PRODUCT_AVAILABILITY,
    salesPriceIncVat = SECOND_MOCKED_PRODUCT_PRICE,
    imageUrl = SECOND_MOCKED_PRODUCT_IMAGE_URL,
    hasNextDayDelivery = SECOND_MOCKED_PRODUCT_NEXT_DAY_DELIVERY
)


val mockedListOfFirstPageProducts = listOf(
    firstMockedProduct,
    secondMockedProduct,
)

val mockedListOfSecondPageProducts = listOf(
    secondMockedProduct,
    firstMockedProduct,
)

val mockedListOfThirdPageProducts = listOf(
    firstMockedProduct,
)

val mockedFirstPage = ProductsPage(
    mockedListOfFirstPageProducts,
    FIRST_PAGE,
    LAST_PAGE
)

val mockedSecondPage = ProductsPage(
    mockedListOfSecondPageProducts,
    SECOND_PAGE,
    LAST_PAGE
)

val mockedThirdPage = ProductsPage(
    mockedListOfThirdPageProducts,
    THIRD_PAGE,
    LAST_PAGE
)