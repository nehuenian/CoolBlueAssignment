package com.example.coolblueassignment.presentation.ui.adapters.viewholders

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.coolblueassignment.presentation.ui.adapters.models.ProductListItem

sealed class ProductListViewHolder(binding: ViewBinding) :
    RecyclerView.ViewHolder(binding.root) {
    abstract fun bind(productListItem: ProductListItem)
}