package com.daniel.myapp.app_tour.ui.home

import androidx.lifecycle.viewModelScope
import com.daniel.myapp.app_friend.base.BaseViewModel
import com.daniel.myapp.app_tour.model.DataProduct
import com.daniel.myapp.app_tour.repo.DataProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repoDataProduct: DataProductRepository
) : BaseViewModel() {

    private val _product = MutableSharedFlow<List<DataProduct>>()
    val product = _product.asSharedFlow()

    fun getProduct(keyword: String = "") = viewModelScope.launch {
        repoDataProduct.getProducts(keyword).collect {
            _product.emit(it)
        }
    }

    fun sortProducts(sortBy: String = "", orderBy: String = "") = viewModelScope.launch {
        repoDataProduct.sortProducts(sortBy, orderBy).collect {
            _product.emit(it)
        }
    }

    fun filterProducts(filter: String = "") = viewModelScope.launch {
        repoDataProduct.filterProducts(filter).collect {
            _product.emit(it)
        }
    }

}