package com.example.coolblueassignment.presentation.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.example.coolblueassignment.R
import com.example.coolblueassignment.databinding.ActivityProductsBinding
import com.example.coolblueassignment.presentation.viewmodel.ProductSearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class ProductsActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private lateinit var binding: ActivityProductsBinding
    private val viewModel: ProductSearchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProductsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(R.id.product_list_container, ProductsFragment())
            .commitNow()

        binding.productSearch.setOnQueryTextListener(this)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        viewModel.updateSearchQuery(query.orEmpty())
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return false
    }
}