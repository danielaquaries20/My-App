package com.daniel.myapp.app_tour.repo

import android.util.Log
import com.crocodic.core.api.ApiObserver
import com.daniel.myapp.app_tour.api.ApiService
import com.daniel.myapp.app_tour.api.response.ProductResponse
import com.daniel.myapp.app_tour.model.DataProduct
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DataProductRepositoryImpl @Inject constructor(private val apiService: ApiService) :
    DataProductRepository {

    override fun getProducts(keyword: String): Flow<List<DataProduct>> = flow {
        ApiObserver.run(
            { apiService.getProducts(keyword) },
            false,
            object : ApiObserver.ModelResponseListener<ProductResponse> {
                override suspend fun onSuccess(response: ProductResponse) {
                    Log.d("API", "Product Response: ${response.products}")
                    emit(response.products)
                }

                override suspend fun onError(response: ProductResponse) {
                    emit(emptyList())
                }
            })
    }

    override fun sortProducts(sortBy: String, order: String): Flow<List<DataProduct>> = flow {
        ApiObserver.run(
            { apiService.sortProducts(sortBy, order) },
            false,
            object : ApiObserver.ModelResponseListener<ProductResponse> {
                override suspend fun onSuccess(response: ProductResponse) {
                    emit(response.products)
                }

                override suspend fun onError(response: ProductResponse) {
                    emit(emptyList())
                }
            })
    }

    override fun filterProducts(filter: String): Flow<List<DataProduct>> = flow {
        ApiObserver.run(
            { apiService.filterProducts(filter) },
            false,
            object : ApiObserver.ModelResponseListener<ProductResponse> {
                override suspend fun onSuccess(response: ProductResponse) {
                    emit(response.products)
                }

                override suspend fun onError(response: ProductResponse) {
                    emit(emptyList())
                }
            })
    }

    override fun pagingProducts(limit: Int, skip: Int): Flow<List<DataProduct>> {
        return flow {
            val response = apiService.pagingProduct(limit, skip)
            emit(response.products)
        }
    }

    override fun getSlider(): Flow<List<DataProduct>> = flow {
        ApiObserver.run(
            { apiService.getSlider() },
            false,
            object : ApiObserver.ModelResponseListener<ProductResponse> {
                override suspend fun onSuccess(response: ProductResponse) {
                    emit(response.products)
                }

                override suspend fun onError(response: ProductResponse) {
                    emit(emptyList())
                }
            })
    }


}