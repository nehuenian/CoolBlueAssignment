package com.example.coolblueassignment.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.PagingData
import com.example.coolblueassignment.R
import com.example.coolblueassignment.databinding.FragmentProductSearchListBinding
import com.example.coolblueassignment.presentation.models.ProductItem
import com.example.coolblueassignment.presentation.ui.adapters.ProductsAdapter
import com.example.coolblueassignment.presentation.viewmodel.ProductSearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class ProductsFragment : Fragment(R.layout.fragment_product_search_list) {
    private var _binding: FragmentProductSearchListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProductSearchViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentProductSearchListBinding.bind(view)
        val adapter = ProductsAdapter().apply {
            addLoadStateListener {
                it.source.refresh.let { loadState ->
                    binding.run {
                        loadingProductsProgressBar.isVisible = loadState is LoadState.Loading
                        productList.isVisible = loadState is LoadState.NotLoading && itemCount > 0
                        noProductsMessage.isVisible =
                            loadState is LoadState.NotLoading && itemCount == 0
                        genericError.genericErrorContainer.isVisible = loadState is LoadState.Error
                    }
                }
            }
        }

        binding.run {
            productList.adapter = adapter

            swipeRefreshLayout.setOnRefreshListener {
                adapter.refresh()
                swipeRefreshLayout.isRefreshing = false
            }
            genericError.genericErrorTryAgain.setOnClickListener {
                adapter.retry()
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.productListResultStream.collectLatest { value: PagingData<ProductItem> ->
                adapter.submitData(value)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}