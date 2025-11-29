package com.daniel.myapp.app_tour.ui.home

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import com.daniel.myapp.app_friend.base.BaseViewModel
import com.daniel.myapp.app_tour.model.DataProduct
import com.daniel.myapp.app_tour.repo.DataProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.crocodic.core.base.adapter.CorePagingSource
import com.crocodic.core.data.CoreSession
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repoDataProduct: DataProductRepository,
    private val coreSession: CoreSession
) : BaseViewModel() {

    val queries = MutableStateFlow<Triple<String?, String?, String?>>(Triple(null, null, null))

    private val myPage = 10

    fun getPagingProducts(): Flow<PagingData<DataProduct>> {
        return queries.flatMapLatest {
            Pager(
                config = CorePagingSource.config(myPage),
                pagingSourceFactory = {
                    CorePagingSource(0) { _, limit: Int ->
                        repoDataProduct.pagingProducts(limit, countPage()).first()
                    }
                }
            ).flow.cachedIn(viewModelScope)
        }
    }

    private fun countPage(): Int {
        val page = coreSession.getInt("page")
        coreSession.setValue("page", page + myPage)
        return page
    }

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