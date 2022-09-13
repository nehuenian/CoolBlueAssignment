package com.example.coolblueassignment.searchproduct

import android.app.Application
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.example.coolblueassignment.R
import com.example.coolblueassignment.data.ProductRepository
import com.example.coolblueassignment.data.TestProductRepository
import com.example.coolblueassignment.espressoutils.verifyItemAtPositionWithValue
import com.example.coolblueassignment.espressoutils.verifyItemIsVisibleAtPosition
import com.example.coolblueassignment.presentation.ui.ProductsActivity
import com.example.coolblueassignment.presentation.ui.adapters.viewholders.ProductListViewHolder
import com.example.coolblueassignment.testutil.*
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@MediumTest
@HiltAndroidTest
class SearchProductTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var repository: ProductRepository

    private val productListMatcher = withId(R.id.product_list)
    private val productNameMatcher = withId(R.id.product_name)
    private val productPriceMatcher = withId(R.id.product_price)
    private val productReviewsMatcher = withId(R.id.product_reviews_counter)
    private val productUSPListMatcher = withId(R.id.usp_list)
    private val productDeliveryTomorrowMatcher = withId(R.id.product_delivery_tomorrow)
    private val productAvailabilityMatcher = withId(R.id.product_availability)
    private val genericErrorMatcher = withText(R.string.generic_error)
    private val tryAgainButtonMatcher = withId(R.id.generic_error_try_again)

    private val reviewsCounterString = ApplicationProvider
        .getApplicationContext<Application>()
        .resources.getQuantityString(
            R.plurals.product_item_number_of_reviews,
            FIRST_MOCKED_PRODUCT_REVIEWS_COUNTER,
            FIRST_MOCKED_PRODUCT_REVIEWS_COUNTER,
        )

    private val availableStoresString = ApplicationProvider
        .getApplicationContext<Application>()
        .resources.getQuantityString(
            R.plurals.product_item_available,
            FIRST_MOCKED_PRODUCT_AVAILABILITY,
            FIRST_MOCKED_PRODUCT_AVAILABILITY,
        )

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun productItemLoadingElements() {
        val activityScenario = ActivityScenario.launch(ProductsActivity::class.java)

        productListMatcher.verifyItemAtPositionWithValue(
            productNameMatcher,
            FIRST_POSITION,
            FIRST_MOCKED_PRODUCT_NAME,
        )

        productListMatcher.verifyItemIsVisibleAtPosition(
            productUSPListMatcher,
            FIRST_POSITION,
        )

        productListMatcher.verifyItemAtPositionWithValue(
            productReviewsMatcher,
            FIRST_POSITION,
            reviewsCounterString,
        )

        productListMatcher.verifyItemAtPositionWithValue(
            productPriceMatcher,
            FIRST_POSITION,
            FIRST_MOCKED_PRODUCT_PRICE.toString(),
        )

        productListMatcher.verifyItemAtPositionWithValue(
            productAvailabilityMatcher,
            FIRST_POSITION,
            availableStoresString
        )

        productListMatcher.verifyItemIsVisibleAtPosition(
            productDeliveryTomorrowMatcher,
            FIRST_POSITION
        )

        activityScenario.close()
    }

    @Test
    fun navigateTroughAllProductPages() {
        val activityScenario = ActivityScenario.launch(ProductsActivity::class.java)

        productListMatcher.scrollToPosition<ProductListViewHolder>(
            SECOND_PAGE_LAST_ITEM_POSITION
        )
        productListMatcher.verifyItemAtPositionWithValue(
            productNameMatcher,
            SECOND_PAGE_LAST_ITEM_POSITION,
            SECOND_PAGE_LAST_MOCKED_PRODUCT_NAME,
        )
        Thread.sleep(500L)
        productListMatcher.scrollToPosition<ProductListViewHolder>(
            LAST_PAGE_LAST_ITEM_POSITION
        )
        productListMatcher.verifyItemAtPositionWithValue(
            productNameMatcher,
            LAST_PAGE_LAST_ITEM_POSITION,
            LAST_MOCKED_PRODUCT_NAME,
        )

        activityScenario.close()
    }

    @Test
    fun errorResponseAndRetry() {
        (repository as TestProductRepository).shouldReturnError = true
        val activityScenario = ActivityScenario.launch(ProductsActivity::class.java)

        onView(genericErrorMatcher).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        (repository as TestProductRepository).shouldReturnError = false

        onView(tryAgainButtonMatcher).perform(ViewActions.click())

        Thread.sleep(1000)

        onView(genericErrorMatcher)
            .check(ViewAssertions.matches(Matchers.not(ViewMatchers.isDisplayed())))

        productListMatcher.verifyItemAtPositionWithValue(
            productNameMatcher,
            FIRST_POSITION,
            FIRST_MOCKED_PRODUCT_NAME,
        )

        activityScenario.close()
    }
}