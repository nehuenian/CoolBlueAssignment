package com.example.coolblueassignment.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.coolblueassignment.R
import com.example.coolblueassignment.databinding.ProductItemBinding
import com.example.coolblueassignment.presentation.models.ProductItem

class ProductsAdapter :
    PagingDataAdapter<ProductItem, ProductsAdapter.ProductViewHolder>(ProductItemDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    class ProductViewHolder private constructor(private val binding: ProductItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(productItem: ProductItem) {
            binding.run {
                productName.text = productItem.name
                productPrice.text = productItem.price.toString()
                productRating.rating = productItem.averageReview.toFloat() / 2F
                productDeliveryTomorrow.isVisible = productItem.hasNextDayDelivery
                productAvailability.text =
                    getProductAvailabilityText(productItem.numberOfStoresAvailable)
                productReviewsCounter.text =
                    getProductReviewsCounterText(productItem.reviewsCounter)
                Glide.with(itemView)
                    .load(productItem.imageUrl)
                    .centerCrop()
                    .placeholder(R.drawable.placeholder_product_image)
                    .into(productImage)
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
        }
    }

    private companion object ProductItemDiffCallback : DiffUtil.ItemCallback<ProductItem>() {
        override fun areItemsTheSame(oldItem: ProductItem, newItem: ProductItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ProductItem, newItem: ProductItem): Boolean {
            return oldItem == newItem
        }
    }
}