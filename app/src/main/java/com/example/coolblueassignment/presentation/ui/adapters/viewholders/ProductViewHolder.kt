package com.example.coolblueassignment.presentation.ui.adapters.viewholders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.example.coolblueassignment.R
import com.example.coolblueassignment.databinding.ProductItemBinding
import com.example.coolblueassignment.presentation.ui.adapters.models.ProductItem
import com.example.coolblueassignment.presentation.ui.adapters.models.ProductListItem
import com.example.coolblueassignment.presentation.util.loadListWithTextViews

class ProductViewHolder(private val binding: ProductItemBinding) : ProductListViewHolder(binding) {

    override fun bind(productListItem: ProductListItem) {
        (productListItem as? ProductItem)?.let { productItem ->
            binding.run {
                productName.text = productItem.name
                productPrice.text = productItem.price.toString()
                productRating.rating = productItem.averageReview
                productDeliveryTomorrow.isVisible = productItem.hasNextDayDelivery
                productAvailability.text =
                    getProductAvailabilityText(productItem.numberOfStoresAvailable)
                productReviewsCounter.text =
                    getProductReviewsCounterText(productItem.reviewsCounter)
                Glide.with(itemView)
                    .load(productItem.imageUrl)
                    .centerInside()
                    .placeholder(R.drawable.placeholder_product_image)
                    .into(productImage)

                uspList.isVisible = productItem.usps.isNotEmpty()
                uspList.removeAllViews()
                uspList.loadListWithTextViews(
                    productItem.usps.take(MAX_NUMBER_OF_USP_ITEMS),
                    R.string.product_item_usp_item
                )
            }
        }
    }

    private fun getProductReviewsCounterText(reviewsCounter: Int): CharSequence {
        return itemView.resources.getQuantityString(
            R.plurals.product_item_number_of_reviews,
            reviewsCounter,
            reviewsCounter
        )
    }

    private fun getProductAvailabilityText(numberOfStoresAvailable: Int): CharSequence {
        return if (numberOfStoresAvailable > 0) {
            itemView.resources.getQuantityString(
                R.plurals.product_item_available,
                numberOfStoresAvailable,
                numberOfStoresAvailable
            )
        } else {
            itemView.context.getString(R.string.product_item_not_available)
        }
    }

    companion object {
        fun from(parent: ViewGroup): ProductViewHolder {
            return ProductViewHolder(
                ProductItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }

        private const val MAX_NUMBER_OF_USP_ITEMS = 5
    }
}