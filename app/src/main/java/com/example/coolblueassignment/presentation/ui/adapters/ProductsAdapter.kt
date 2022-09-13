package com.example.coolblueassignment.presentation.ui.adapters

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.coolblueassignment.presentation.ui.adapters.models.ProductListItem
import com.example.coolblueassignment.presentation.ui.adapters.viewholders.ProductListViewHolder

class ProductsAdapter :
    PagingDataAdapter<ProductListItem, ProductListViewHolder>(ProductListItemDiffCallback) {

    override fun getItemViewType(position: Int): Int = position

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ProductListViewHolder {
        getItem(position)?.let { item ->
            return ProductListViewHolderFactory.createViewHolder(item, parent)
        } ?: run {
            // null should not be possible because we're disabling placeholders for this PagerSource
            throw IllegalArgumentException("No item at position $position")
        }
    }

    override fun onBindViewHolder(holder: ProductListViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    private companion object ProductListItemDiffCallback :
        DiffUtil.ItemCallback<ProductListItem>() {
        override fun areItemsTheSame(oldItem: ProductListItem, newItem: ProductListItem): Boolean {
            return oldItem.getIdentityValue() == newItem.getIdentityValue()
        }

        override fun areContentsTheSame(
            oldItem: ProductListItem,
            newItem: ProductListItem
        ): Boolean {
            return oldItem == newItem
        }
    }
}