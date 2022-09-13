package com.example.coolblueassignment.presentation.ui.adapters

import android.view.ViewGroup
import com.example.coolblueassignment.presentation.ui.adapters.models.ProductItem
import com.example.coolblueassignment.presentation.ui.adapters.models.ProductListItem
import com.example.coolblueassignment.presentation.ui.adapters.viewholders.ProductListViewHolder
import com.example.coolblueassignment.presentation.ui.adapters.viewholders.ProductViewHolder

interface ProductListViewHolderFactory {
    companion object {
        fun createViewHolder(item: ProductListItem, parent: ViewGroup): ProductListViewHolder {
            return when (item) {
                is ProductItem -> ProductViewHolder.from(parent)
            }
        }
    }
}