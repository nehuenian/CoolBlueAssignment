package com.example.coolblueassignment.presentation.ui.adapters.models

sealed class ProductListItem {
    abstract fun getIdentityValue(): Comparable<*>
}
